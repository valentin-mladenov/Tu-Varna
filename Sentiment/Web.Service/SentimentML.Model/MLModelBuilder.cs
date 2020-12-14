using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using Microsoft.ML;
using Microsoft.ML.Data;
using static Microsoft.ML.DataOperationsCatalog;
using SentimentML.Model.Models;
using System.Data.Common;
using System.Data.SqlClient;

namespace SentimentML.Model
{
    public class MLModelBuilder
    {
        public void Init()
        {
            MLContext mlContext = new MLContext();
            TrainTestData splitDataView = LoadData(mlContext);
            ITransformer model = BuildAndTrainModel(mlContext, splitDataView.TrainSet);
            Evaluate(mlContext, model, splitDataView.TestSet);
            UseModelWithSingleItem(mlContext, model);
            UseModelWithBatchItems(mlContext, model);
            SaveModel(mlContext, model, Constants.ModelFilePath, splitDataView.TrainSet.Schema);
        }

        public void InitSQL()
        {
            MLContext mlContext = new MLContext();
            TrainTestData splitDataView = LoadDataFromSQL(mlContext);
            ITransformer model = BuildAndTrainModel(mlContext, splitDataView.TrainSet);
            Evaluate(mlContext, model, splitDataView.TestSet);
            UseModelWithSingleItem(mlContext, model);
            UseModelWithBatchItems(mlContext, model);
            SaveModel(mlContext, model, Constants.ModelFilePath, splitDataView.TrainSet.Schema);
        }

        private static void SaveModel(MLContext mlContext, ITransformer mlModel, string modelRelativePath, DataViewSchema modelInputSchema)
        {
            // Save/persist the trained model to a .ZIP file
            Console.WriteLine($"=============== Saving the model  ===============");
            mlContext.Model.Save(mlModel, modelInputSchema, GetAbsolutePath(modelRelativePath));
            Console.WriteLine("The model is saved to {0}", GetAbsolutePath(modelRelativePath));
        }

        public static string GetAbsolutePath(string relativePath)
        {
            FileInfo dataRoot = new FileInfo(typeof(MLModelBuilder).Assembly.Location);
            string assemblyFolderPath = dataRoot.Directory.FullName;

            string fullPath = Path.Combine(assemblyFolderPath, relativePath);

            return fullPath;
        }

        public static void UseModelWithBatchItems(MLContext mlContext, ITransformer model)
        {
            IList<SentimentData> sentiments = new[]
            {
                new SentimentData
                {
                    SentimentText = "This was a horrible meal"
                },
                new SentimentData
                {
                    SentimentText = "I love this spaghetti."
                }
            };

            IDataView batchComments = mlContext.Data.LoadFromEnumerable(sentiments);

            IDataView predictions = model.Transform(batchComments);

            // Use model to predict whether comment data is Positive (1) or Negative (0).
            var predictedResults = mlContext.Data
                .CreateEnumerable<SentimentPrediction>(predictions, reuseRowObject: false)
                .ToList();

            Console.WriteLine();

            Console.WriteLine("=============== Prediction Test of loaded model with multiple samples ===============");

            for (int i = 0; i < predictedResults.Count(); i++)
            {
                Console.WriteLine($"Sentiment: {sentiments[i].SentimentText} | Prediction: {(Convert.ToBoolean(predictedResults[i].Prediction) ? "Positive" : "Negative")} | Probability: {predictedResults[i].Probability} | Score: {predictedResults[i].Score}");

            }
            Console.WriteLine("=============== End of predictions ===============");
        }

        private static void UseModelWithSingleItem(MLContext mlContext, ITransformer model)
        {
            PredictionEngine<SentimentData, SentimentPrediction> predictionFunction = mlContext.Model.CreatePredictionEngine<SentimentData, SentimentPrediction>(model);
            SentimentData sampleStatement = new SentimentData
            {
                SentimentText = "This was a very bad steak"
            };
            var resultPrediction = predictionFunction.Predict(sampleStatement);

            Console.WriteLine();
            Console.WriteLine("=============== Prediction Test of model with a single sample and test dataset ===============");

            Console.WriteLine();
            Console.WriteLine($"Sentiment: {sampleStatement.SentimentText} | Prediction: {(Convert.ToBoolean(resultPrediction.Prediction) ? "Positive" : "Negative")} | Probability: {resultPrediction.Probability} | Score: {resultPrediction.Score}");

            Console.WriteLine("=============== End of Predictions ===============");
            Console.WriteLine();
        }

        public static void Evaluate(
            MLContext mlContext, ITransformer model, IDataView splitTestSet)
        {
            Console.WriteLine("=============== Evaluating Model accuracy with Test data===============");
            IDataView predictions = model.Transform(splitTestSet);
            BinaryClassificationMetrics metrics = mlContext.BinaryClassification
                .EvaluateNonCalibrated(predictions, "Sentiment");

            Console.WriteLine();
            Console.WriteLine("Model quality metrics evaluation");
            Console.WriteLine("--------------------------------");
            Console.WriteLine($"Accuracy: {metrics.Accuracy:P2}");
            Console.WriteLine($"Auc: {metrics.AreaUnderRocCurve:P2}");
            Console.WriteLine($"F1Score: {metrics.F1Score:P2}");
            Console.WriteLine($"Negative Precision: {metrics.NegativePrecision:F2}");

            Console.WriteLine($"Negative Recall: {metrics.NegativeRecall:F2}");
            Console.WriteLine($"Positive Precision: {metrics.PositivePrecision:F2}");

            Console.WriteLine($"Positive Recall: {metrics.PositiveRecall:F2}\n");
            Console.WriteLine(metrics.ConfusionMatrix.GetFormattedConfusionTable());
            Console.WriteLine("=============== End of model evaluation ===============");
        }

        public static ITransformer BuildAndTrainModel(MLContext mlContext, IDataView splitTrainSet)
        {
            var estimator = mlContext.Transforms.Text.FeaturizeText(
                outputColumnName: "Features",
                inputColumnName: nameof(SentimentData.SentimentText))
            .Append(
                mlContext.BinaryClassification.Trainers.AveragedPerceptron(
                    labelColumnName: "Sentiment",
                    featureColumnName: "Features"));

            Console.WriteLine("=============== Create and Train the Model ===============");
            var model = estimator.Fit(splitTrainSet);
            Console.WriteLine("=============== End of training ===============");
            Console.WriteLine();

            return model;
        }

        public static TrainTestData LoadData(MLContext mlContext)
        {
            var dataView = mlContext.Data.LoadFromTextFile<SentimentData>(
                Constants.TrainDataURL,
                hasHeader: true,
                separatorChar: '\t',
                allowQuoting: true);

            var splitDataView = mlContext.Data.TrainTestSplit(dataView, testFraction: 0.2);

            return splitDataView;
        }

        public static TrainTestData LoadDataFromSQL(MLContext mlContext)
        {
            var dataLoader = mlContext.Data.CreateDatabaseLoader<SentimentData>();

            DbProviderFactories.RegisterFactory("System.Data.SqlClient", SqlClientFactory.Instance);
            var factory = DbProviderFactories.GetFactory("System.Data.SqlClient");
            var databaseSource = new DatabaseSource(factory, connectionString: "Server=.\\SQLEXPRESS;Database=Sentiment.DB;Integrated Security=True;",
                commandText: "SELECT [Sentiment], [Text] AS SentimentText " +
                "FROM [Sentiment.DB].[dbo].[MLInputFeedbacks] " +
                "WHERE [Sentiment] IS NOT NULL");

            var dataView = dataLoader.Load(databaseSource);

            var splitDataView = mlContext.Data.TrainTestSplit(dataView, testFraction: 0.2);

            return splitDataView;
        }
    }
}
