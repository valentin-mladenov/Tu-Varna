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
            ITransformer mlModel = mlContext.Model
                .Load(Constants.ConsumeModelURL, out var modelInputSchema);

            Console.WriteLine(modelInputSchema.ToString());

            var predEngine = mlContext.Model
                .CreatePredictionEngine<ModelInput, ModelOutput>(mlModel);

            return predEngine;
        }
    }
}
