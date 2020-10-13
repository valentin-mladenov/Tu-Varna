using SentimentML.Model;
using SentimentWeb.Service.Data.Entities;

namespace SentimentWeb.Service.Data.Services.Interfaces
{
    public interface IPredictionService
    {
        CustomerFeedback Predict(string feedback);
    }
}
