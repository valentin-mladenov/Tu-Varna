using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using SentimentWeb.Service.Data;
using SentimentWeb.Service.Data.Entities;
using SentimentWeb.Service.Data.Repositories;
using SentimentWeb.Service.Data.Repositories.Interfaces;
using SentimentWeb.Service.Data.Services.Interfaces;
using SentimentWeb.Service.DTOs;

namespace SentimentWeb.Service.Data.Repositories
{
    public class CustomerFBRepository : ICustomerFBRepository
    {
        private SentimentWebContext _dbContext = null;
        private readonly IPredictionService predictionService;


        public CustomerFBRepository(SentimentWebContext context, IPredictionService predictionService)
        {
            _dbContext = context;
            this.predictionService = predictionService;
        }


        public CustomerFeedback Get(Guid id)
        {
            var feedback = _dbContext.CustomerFeedbacks.Find(id);

            return feedback;
        }


        public IEnumerable<CustomerFeedback> GetPage(int pageNumber, int pageSize)
        {
            var feedbacks = _dbContext.CustomerFeedbacks
                .Skip(pageNumber * pageSize)
                .Take(pageSize)
                .ToList();

            return feedbacks;
        }

        public async Task<CustomerFeedback> PostFeedback(FeedbackModel feedback)
        {
            var prediction = this.predictionService.Predict(feedback.Text);
            prediction.Text = feedback.Text;

            _dbContext.CustomerFeedbacks.Add(prediction);
            await _dbContext.SaveChangesAsync();

            return prediction;
        }


        public IEnumerable<PieChartElement> GetChartData()
        {
            var positives = _dbContext.CustomerFeedbacks.Count(f => f.ConfirmedSentiment || f.SentimentScore > 0.15m);
            var negatives = _dbContext.CustomerFeedbacks.Count(f => (f.SentToML && !f.ConfirmedSentiment) || f.SentimentScore < -0.15m);

            var neutrals = _dbContext.CustomerFeedbacks.Count() - positives - negatives;

            var chartDS = new List<PieChartElement>();

            chartDS.Add(new PieChartElement
            {
                Name = "positives",
                Value = positives
            });

            chartDS.Add(new PieChartElement
            {
                Name = "negatives",
                Value = negatives
            });

            chartDS.Add(new PieChartElement
            {
                Name = "neutrals",
                Value = neutrals
            });

            return chartDS;
        }
    }
}