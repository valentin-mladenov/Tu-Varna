using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using LanguageML.Model.Models;
using Microsoft.ML;
using Microsoft.ML.Data;

namespace LanguageML.Model
{
    public static class ModelBuilder
    {
        //private static string TRAIN_DATA_FILEPATH = @"C:\Users\hudso\source\repos\Tu-Varna\Sentiment\Web.Service\LanguageML.Model\Data\all_words1.tsv";
        //private static string MODEL_FILEPATH = @"C:\Users\hudso\source\repos\Tu-Varna\Sentiment\Web.Service\LanguageML.Model\MLModel.zip";
        //private static string MODEL_FILEPATH_RELATIVE = @"..\..\MLModel.zip";
        // Create MLContext to be shared across the model creation workflow objects 
        // Set a random seed for repeatable/deterministic results across multiple trainings.
        private static MLContext mlContext = new MLContext(seed: 1);

        public static void Init()
        {
            FileInfo _dataRoot = new FileInfo(typeof(ModelBuilder).Assembly.Location);

            string assemblyFolderPath = _dataRoot.Directory.FullName;

            string fullPath = Path.Combine(assemblyFolderPath, Constants.ModelFilePath);

            //var trainingDataView = LoadDataFromFile();
            //CreateModel(trainingDataView);
            //Evaulate();
        }

        public static void InitSQL()
        {
            var trainingDataView = LoadDataFromSQL();
            CreateModel(trainingDataView);
            Evaulate();
        }

        public static IDataView LoadDataFromFile()
        {
            // Load Data
             return mlContext.Data
                .LoadFromTextFile<ModelInput>(
                    path: Constants.TrainDataURL,
                    hasHeader: true,
                    separatorChar: '\t',
                    allowQuoting: true,
                    allowSparse: false);
        }

        public static IDataView LoadDataFromSQL()
        {
            // Load Data
            return mlContext.Data
               .LoadFromTextFile<ModelInput>(
                   path: Constants.TrainDataURL,
                   hasHeader: true,
                   separatorChar: '\t',
                   allowQuoting: true,
                   allowSparse: false);
        }

        public static void CreateModel(IDataView trainingDataView)
        {
            // Build training pipeline
            IEstimator<ITransformer> trainingPipeline = BuildTrainingPipeline(mlContext);

            // Train Model
            ITransformer mlModel = TrainModel(trainingDataView, trainingPipeline);

            // Evaluate quality of Model
            Evaluate(mlContext, trainingDataView, trainingPipeline);

            // Save model
            SaveModel(mlContext, mlModel, Constants.ModelFilePath, trainingDataView.Schema);
        }

        public static IEstimator<ITransformer> BuildTrainingPipeline(
            MLContext mlContext)
        {
            // Data process configuration with pipeline data transformations 
            var dataProcessPipeline = mlContext.Transforms.Conversion
                .MapValueToKey("Language", "Language")
                .Append(mlContext.Transforms.Text.FeaturizeText("Text_tf", "Text"))
                .Append(mlContext.Transforms.CopyColumns("Features", "Text_tf"))
                .Append(mlContext.Transforms.NormalizeMinMax("Features", "Features"))
                .AppendCacheCheckpoint(mlContext);

            var training = mlContext.BinaryClassification.Trainers
                    .LinearSvm(labelColumnName: "Language", featureColumnName: "Features");

            var transformator = mlContext.Transforms.Conversion
                .MapKeyToValue("PredictedLabel", "PredictedLabel");

            // Set the training algorithm 
            var trainer = mlContext.MulticlassClassification.Trainers
                .OneVersusAll(training, labelColumnName: "Language")
                .Append(transformator);

            var trainingPipeline = dataProcessPipeline.Append(trainer);

            return trainingPipeline;
        }

        public static ITransformer TrainModel(
            IDataView trainingDataView,
            IEstimator<ITransformer> trainingPipeline)
        {
            Console.WriteLine("=============== Training  model ===============");

            ITransformer model = trainingPipeline.Fit(trainingDataView);

            Console.WriteLine("=============== End of training process ===============");
            return model;
        }

        private static void Evaluate(
            MLContext mlContext,
            IDataView trainingDataView,
            IEstimator<ITransformer> trainingPipeline)
        {
            // Cross-Validate with single dataset (since we don't have two datasets, one for training and for evaluate)
            // in order to evaluate and get the model's accuracy metrics
            Console.WriteLine("=============== Cross-validating to get model's accuracy metrics ===============");
            var crossValidationResults = mlContext
                .MulticlassClassification.CrossValidate(
                    trainingDataView,
                    trainingPipeline,
                    numberOfFolds: 5,
                    labelColumnName: "Language");
            PrintMulticlassClassificationFoldsAverageMetrics(crossValidationResults);
        }

        private static void SaveModel(
            MLContext mlContext,
            ITransformer mlModel,
            string modelRelativePath,
            DataViewSchema modelInputSchema)
        {
            // Save/persist the trained model to a .ZIP file
            Console.WriteLine($"=============== Saving the model  ===============");
            mlContext.Model.Save(mlModel, modelInputSchema, GetAbsolutePath(modelRelativePath));
            Console.WriteLine("The model is saved to {0}", GetAbsolutePath(modelRelativePath));
        }

        public static string GetAbsolutePath(string relativePath)
        {
            //FileInfo _dataRoot = new FileInfo(typeof(Program).Assembly.Location);
            //string assemblyFolderPath = _dataRoot.Directory.FullName;

            //string fullPath = Path.Combine(assemblyFolderPath, relativePath);

            return relativePath;
        }

        public static void PrintMulticlassClassificationMetrics(
            MulticlassClassificationMetrics metrics)
        {
            Console.WriteLine($"************************************************************");
            Console.WriteLine($"*    Metrics for multi-class classification model   ");
            Console.WriteLine($"*-----------------------------------------------------------");
            Console.WriteLine($"    MacroAccuracy = {metrics.MacroAccuracy:0.####}, a value between 0 and 1, the closer to 1, the better");
            Console.WriteLine($"    MicroAccuracy = {metrics.MicroAccuracy:0.####}, a value between 0 and 1, the closer to 1, the better");
            Console.WriteLine($"    LogLoss = {metrics.LogLoss:0.####}, the closer to 0, the better");

            for (int i = 0; i < metrics.PerClassLogLoss.Count; i++)
            {
                Console.WriteLine($"    LogLoss for class {i + 1} = {metrics.PerClassLogLoss[i]:0.####}, the closer to 0, the better");
            }

            Console.WriteLine($"************************************************************");
        }

        public static void PrintMulticlassClassificationFoldsAverageMetrics(
            IEnumerable<TrainCatalogBase.CrossValidationResult<MulticlassClassificationMetrics>> crossValResults)
        {
            var metricsInMultipleFolds = crossValResults.Select(r => r.Metrics);

            var microAccuracyValues = metricsInMultipleFolds.Select(m => m.MicroAccuracy);
            var microAccuracyAverage = microAccuracyValues.Average();
            var microAccuraciesStdDeviation = CalculateStandardDeviation(microAccuracyValues);
            var microAccuraciesConfidenceInterval95 = CalculateConfidenceInterval95(microAccuracyValues);

            var macroAccuracyValues = metricsInMultipleFolds.Select(m => m.MacroAccuracy);
            var macroAccuracyAverage = macroAccuracyValues.Average();
            var macroAccuraciesStdDeviation = CalculateStandardDeviation(macroAccuracyValues);
            var macroAccuraciesConfidenceInterval95 = CalculateConfidenceInterval95(macroAccuracyValues);

            var logLossValues = metricsInMultipleFolds.Select(m => m.LogLoss);
            var logLossAverage = logLossValues.Average();
            var logLossStdDeviation = CalculateStandardDeviation(logLossValues);
            var logLossConfidenceInterval95 = CalculateConfidenceInterval95(logLossValues);

            var logLossReductionValues = metricsInMultipleFolds.Select(m => m.LogLossReduction);
            var logLossReductionAverage = logLossReductionValues.Average();
            var logLossReductionStdDeviation = CalculateStandardDeviation(logLossReductionValues);
            var logLossReductionConfidenceInterval95 = CalculateConfidenceInterval95(logLossReductionValues);

            Console.WriteLine($"*************************************************************************************************************");
            Console.WriteLine($"*       Metrics for Multi-class Classification model      ");
            Console.WriteLine($"*------------------------------------------------------------------------------------------------------------");
            Console.WriteLine($"*       Average MicroAccuracy:    {microAccuracyAverage:0.###}  - Standard deviation: ({microAccuraciesStdDeviation:#.###})  - Confidence Interval 95%: ({microAccuraciesConfidenceInterval95:#.###})");
            Console.WriteLine($"*       Average MacroAccuracy:    {macroAccuracyAverage:0.###}  - Standard deviation: ({macroAccuraciesStdDeviation:#.###})  - Confidence Interval 95%: ({macroAccuraciesConfidenceInterval95:#.###})");
            Console.WriteLine($"*       Average LogLoss:          {logLossAverage:#.###}  - Standard deviation: ({logLossStdDeviation:#.###})  - Confidence Interval 95%: ({logLossConfidenceInterval95:#.###})");
            Console.WriteLine($"*       Average LogLossReduction: {logLossReductionAverage:#.###}  - Standard deviation: ({logLossReductionStdDeviation:#.###})  - Confidence Interval 95%: ({logLossReductionConfidenceInterval95:#.###})");
            Console.WriteLine($"*************************************************************************************************************");
        }

        public static double CalculateStandardDeviation(IEnumerable<double> values)
        {
            double average = values.Average();
            double sumOfSquaresOfDifferences = values.Select(val => (val - average) * (val - average)).Sum();
            double standardDeviation = Math.Sqrt(sumOfSquaresOfDifferences / (values.Count() - 1));
            return standardDeviation;
        }

        public static double CalculateConfidenceInterval95(IEnumerable<double> values)
        {
            double confidenceInterval95 = 1.96 * CalculateStandardDeviation(values) / Math.Sqrt((values.Count() - 1));
            return confidenceInterval95;
        }

        public static void Evaulate()
        {
            // Create single instance of sample data from first line of dataset for model input
            ModelInput sampleData = new ModelInput()
            {
                Text = @"а вземе пари а ги изхарчи",
            };

            // Make a single prediction on the sample data and print results
            var predictionResult = ConsumeLanguageModel.Predict(sampleData);

            Console.WriteLine("Using model to make single prediction -- Comparing actual Language with predicted Language from sample data...\n\n");
            Console.WriteLine($"Text: {sampleData.Text}");
            Console.WriteLine($"\n\nPredicted Language value {predictionResult.Prediction} \nPredicted Language scores: [{String.Join(",", predictionResult.Score)}]\n\n");
            Console.WriteLine("=============== End of process, hit any key to finish ===============");
            Console.ReadKey();
        }
    }
}
