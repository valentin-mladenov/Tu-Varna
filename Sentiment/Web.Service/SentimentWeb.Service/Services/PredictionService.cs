using SentimentML.Model;
using SentimentML.Model.Models;
using SentimentWeb.Service.Data.Entities;
using SentimentWeb.Service.Data.Services.Interfaces;


namespace SentimentWeb.Service.Data.Services
{
    public class PredictionService : IPredictionService
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
