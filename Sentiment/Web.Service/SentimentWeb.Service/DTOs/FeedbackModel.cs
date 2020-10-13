using System;

namespace SentimentWeb.Service.DTOs
{
    public class FeedbackModel
    {
        public long Id { get; set; }
        public string Text { get; set; }
        public bool Sentiment { get; set; }
        public bool ConfirmedSentiment { get; set; }
        public decimal Probability { get; set; }
        public decimal Score { get; set; }
        public bool SentToML { get; set; }
        public string UserName { get; set; }
    }
}