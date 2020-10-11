using AFE.Service.Data;
using AFE.Service.Data.Entities;
using AFE.Service.Data.Repositories;
using AFE.Service.Data.Repositories.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using AFE.Service.Data.Services.Interfaces;
using AFE.Service.DTOs;

namespace AFE.Service.Data.Repositories
{
    public class CustomerFBRepository : ICustomerFBRepository
    {
        private AFEContext _dbContext = null;
        private readonly IPredictionRepository _predictionRepository;


        public CustomerFBRepository(AFEContext context, IPredictionRepository predictionRepository)
        {
            _dbContext = context;
            _predictionRepository = predictionRepository;
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

        public async Task<bool> PostFeedback(FeedbackModel feedback)
        {
            var prediction = this._predictionRepository.Predict(feedback.Text);
            prediction.Text = feedback.Text;
            prediction.UserName = feedback.UserName;

            _dbContext.CustomerFeedbacks.Add(prediction);
            await _dbContext.SaveChangesAsync();

            return prediction.Sentiment;
        }


        public IEnumerable<PieChartElement> GetChartData()
        {
            var positives = _dbContext.CustomerFeedbacks.Count(f => f.Score > 0.15m);
            var negatives = _dbContext.CustomerFeedbacks.Count(f => f.Score < -0.15m);
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