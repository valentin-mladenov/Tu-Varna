using AFE.Model.Fabric.Models;
using Microsoft.ML;

namespace AFE.Model.Fabric
{
    public class ConsumeModel
    {
        public static SentimentPrediction Predict(SentimentData input)
        {

            // Create new MLContext
            MLContext mlContext = new MLContext();

            // Load model & create prediction engine
            string modelPath = Constants.ConsumeModelURL;
            ITransformer mlModel = mlContext.Model.Load(modelPath, out var modelInputSchema);
            var predEngine = mlContext.Model.CreatePredictionEngine<SentimentData, SentimentPrediction>(mlModel);

            // Use model to make prediction on input data
            SentimentPrediction result = predEngine.Predict(input);
            return result;
        }
    }
}
