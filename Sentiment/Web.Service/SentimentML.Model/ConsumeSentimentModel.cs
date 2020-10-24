using SentimentML.Model.Models;
using Microsoft.ML;

namespace SentimentML.Model
{
    public class ConsumeSentimentModel
    {
        public static SentimentPrediction Predict(SentimentData input)
        {
            // Create new MLContext
            MLContext mlContext = new MLContext();

            // Load model & create prediction engine

            ITransformer mlModel = mlContext.Model.Load(
                MLModelBuilder.GetAbsolutePath(Constants.ModelFilePath),
                out var modelInputSchema);

            System.Console.WriteLine(modelInputSchema);

            var predEngine = mlContext.Model
                .CreatePredictionEngine<SentimentData, SentimentPrediction>(mlModel);

            // Use model to make prediction on input data
            SentimentPrediction result = predEngine.Predict(input);

            return result;
        }
    }
}
