using SentimentWeb.Service.Data.Entities;
using System;

namespace SentimentWeb.Service.DTOs
{
    public class FeedbackModel
    {
        public long Ident { get; set; }
        public string Text { get; set; }
        public bool Sentiment { get; set; }
        public bool ConfirmedSentiment { get; set; }
        public decimal Probability { get; set; }
        public decimal Score { get; set; }
        public bool SentToML { get; set; }
        public string Language { get; set; }
        public string ConfirmedLanguage { get; set; }
        public LanguageScore LanguageScore { get; set; }
        public Sex Sex { get; set; }
        public MaritalStatus MaritalStatus { get; set; }
        public AgeRange AgeRange { get; set; }
    }
}