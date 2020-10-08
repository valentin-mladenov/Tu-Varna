using AFE.Model.Fabric;
using AFE.Model.Fabric.Models;
using AFE.Service.Data.Entities;
using AFE.Service.Data.Services.Interfaces;


namespace AFE.Service.Data.Services
{
    public class PredictionRepository : IPredictionRepository
    {
        public CustomerFeedback Predict(string feedback)
        {
            var input = new SentimentData();
            input.SentimentText = feedback;

            // Load model and predict output of sample data
            SentimentPrediction result = ConsumeModel.Predict(input);

            var customerFeedback = new CustomerFeedback()
            {
                Probability = (decimal)result.Probability,
                Score = (decimal)result.Score,
                Sentiment = result.Prediction
            };

            return customerFeedback;
        }
    }
}
