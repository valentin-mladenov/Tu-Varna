using SentimentML.Model;
using SentimentML.Model.Models;
using LanguageML.Model;
using LanguageML.Model.Models;
using SentimentWeb.Service.Data.Entities;
using SentimentWeb.Service.Data.Services.Interfaces;


namespace SentimentWeb.Service.Data.Services
{
    public class PredictionService : IPredictionService
    {
        public CustomerFeedback Predict(string feedback)
        {
            // Load model and predict output of sample data
            var sentimentResult = ConsumeSentimentModel.Predict(
                new SentimentData()
                {
                    SentimentText = feedback
                });

            // Load model and predict output of sample data
            var langResult = ConsumeLanguageModel.Predict(
                new ModelInput()
                {
                    Text = feedback
                });

            var customerFeedback = new CustomerFeedback()
            {
                SentimentProbability = (decimal)sentimentResult.Probability,
                SentimentScore = (decimal)sentimentResult.Score,
                Sentiment = sentimentResult.Prediction,
                Language = langResult.Prediction,
                LanguageScore = new LanguageScore()
                {
                    BG = langResult.Score[0],
                    DE = langResult.Score[1],
                    EN = langResult.Score[2],
                    RU = langResult.Score[3]
                }
            };

            return customerFeedback;
        }
    }
}
