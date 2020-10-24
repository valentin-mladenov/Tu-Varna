using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
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
            prediction.AgeRange = feedback.AgeRange;
            prediction.MaritalStatus = feedback.MaritalStatus;
            prediction.Sex = feedback.Sex;

            _dbContext.CustomerFeedbacks.Add(prediction);
            await _dbContext.SaveChangesAsync();

            return prediction;
        }


        public IEnumerable<PieChart> GetChartData()
        {
            var dbCustomerFeedback = _dbContext.CustomerFeedbacks;

            var result = new List<PieChart>
            {
                GetTotalsChart(dbCustomerFeedback),
                GetMaritalStatusChart(dbCustomerFeedback),
                GetGenderChart(dbCustomerFeedback),
                GetAgeRangeChart(dbCustomerFeedback)
            };

            return result;
        }

        private PieChart GetTotalsChart(DbSet<CustomerFeedback> dbCustomerFeedback)
        {
            var positives = dbCustomerFeedback.Count(f => (f.SentToML && f.ConfirmedSentiment) || (!f.SentToML && f.SentimentScore > 0.15m));
            var negatives = dbCustomerFeedback.Count(f => (f.SentToML && !f.ConfirmedSentiment) || (!f.SentToML && f.SentimentScore < -0.15m));
            var neutrals = dbCustomerFeedback.Count() - positives - negatives;

            var chart = new PieChart()
            {
                Name = "Total",
                Value = new List<PieChartElement>()
            };

            chart.Value.Add(new PieChartElement
            {
                Name = "positives",
                Value = positives
            });

            chart.Value.Add(new PieChartElement
            {
                Name = "negatives",
                Value = negatives
            });

            chart.Value.Add(new PieChartElement
            {
                Name = "neutrals",
                Value = neutrals
            });

            return chart;
        }

        private PieChart GetMaritalStatusChart(DbSet<CustomerFeedback> dbCustomerFeedback)
        {
            var chart = new PieChart()
            {
                Name = "Marital status",
                Value = new List<PieChartElement> {
                    new PieChartElement
                    {
                        Name = "single",
                        Value = dbCustomerFeedback.Count(f => f.MaritalStatus == MaritalStatus.Single)
                    },
                    new PieChartElement
                    {
                        Name = "married",
                        Value = dbCustomerFeedback.Count(f => f.MaritalStatus == MaritalStatus.Married)
                    },
                    new PieChartElement
                    {
                        Name = "devorced",
                        Value = dbCustomerFeedback.Count(f => f.MaritalStatus == MaritalStatus.Devorced)
                    },
                    new PieChartElement
                    {
                        Name = "widowed",
                        Value = dbCustomerFeedback.Count(f => f.MaritalStatus == MaritalStatus.Widowed)
                    }
                }
            };

            return chart;
        }

        private PieChart GetGenderChart(DbSet<CustomerFeedback> dbCustomerFeedback)
        {
            var chart = new PieChart()
            {
                Name = "Marital status",
                Value = new List<PieChartElement> {
                    new PieChartElement
                    {
                        Name = "female",
                        Value = dbCustomerFeedback.Count(f => f.Sex == Sex.Female)
                    },
                    new PieChartElement
                    {
                        Name = "male",
                        Value = dbCustomerFeedback.Count(f => f.Sex == Sex.Male)
                    }
                }
            };

            return chart;
        }
        private PieChart GetAgeRangeChart(DbSet<CustomerFeedback> dbCustomerFeedback)
        {
            var chart = new PieChart()
            {
                Name = "Age Range",
                Value = new List<PieChartElement> {
                    new PieChartElement
                    {
                        Name = "10-15",
                        Value = dbCustomerFeedback.Count(f => f.AgeRange == AgeRange.From10Till15)
                    },
                    new PieChartElement
                    {
                        Name = "16-19",
                        Value = dbCustomerFeedback.Count(f => f.AgeRange == AgeRange.From16Till19)
                    },
                    new PieChartElement
                    {
                        Name = "20-25",
                        Value = dbCustomerFeedback.Count(f => f.AgeRange == AgeRange.From20Till25)
                    },
                    new PieChartElement
                    {
                        Name = "26-35",
                        Value = dbCustomerFeedback.Count(f => f.AgeRange == AgeRange.From26Till35)
                    },
                    new PieChartElement
                    {
                        Name = "36-45",
                        Value = dbCustomerFeedback.Count(f => f.AgeRange == AgeRange.From36Till45)
                    },
                    new PieChartElement
                    {
                        Name = "46-55",
                        Value = dbCustomerFeedback.Count(f => f.AgeRange == AgeRange.From46Till55)
                    },
                    new PieChartElement
                    {
                        Name = "56-65",
                        Value = dbCustomerFeedback.Count(f => f.AgeRange == AgeRange.From56Till65)
                    },
                    new PieChartElement
                    {
                        Name = "66-∞",
                        Value = dbCustomerFeedback.Count(f => f.AgeRange == AgeRange.From66ToInfinity)
                    }
                }
            };

            return chart;
        }
    }
}