using System;
using LanguageML.Model.Models;
using Microsoft.ML;

namespace LanguageML.Model
{
    public class ConsumeLanguageModel
    {
        private static Lazy<PredictionEngine<ModelInput, ModelOutput>> PredictionEngine = 
            new Lazy<PredictionEngine<ModelInput, ModelOutput>>(CreatePredictionEngine);

        public static ModelOutput Predict(ModelInput input)
        {
            ModelOutput result = PredictionEngine.Value.Predict(input);
            return result;
        }

        public static PredictionEngine<ModelInput, ModelOutput> CreatePredictionEngine()
        {
            // Create new MLContext
            MLContext mlContext = new MLContext();

            // Load model & create prediction engine
            string modelPath = @"C:\Users\hudso\source\repos\Tu-Varna\Sentiment\Web.Service\LanguageML.Model\MLModel.zip";
            ITransformer mlModel = mlContext.Model
                .Load(modelPath, out var modelInputSchema);

            var predEngine = mlContext.Model
                .CreatePredictionEngine<ModelInput, ModelOutput>(mlModel);

            return predEngine;
        }
    }
}
