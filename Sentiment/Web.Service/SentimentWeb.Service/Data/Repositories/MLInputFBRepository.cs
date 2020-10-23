using SentimentWeb.Service.Data.Entities;
using SentimentWeb.Service.Data.Repositories.Interfaces;
using SentimentWeb.Service.DTOs;
using System;
using System.Collections.Generic;
using System.Linq;

namespace SentimentWeb.Service.Data.Repositories
{
	public class MLInputFBRepository : IMLInputFBRepository
	{
		private SentimentWebContext _dbContext = null;


		public MLInputFBRepository(SentimentWebContext context)
		{
			_dbContext = context;
		}

		public bool TransferToMLInput()
		{
			var customerFeedbacks = _dbContext.CustomerFeedbacks.Where(f => !f.SentToML).ToList();

			var mlInputs = new List<MLInputFeedback>();
			foreach (var cfb in customerFeedbacks)
			{
				var mli = new MLInputFeedback
				{
					Sentiment = cfb.Sentiment,
					Text = cfb.Text
				};

				_dbContext.MLInputFeedbacks.Add(mli);
				cfb.SentToML = true;
			}

			_dbContext.SaveChanges();

			return true;
		}


		public bool ConfirmFeeback(FeedbackModel customerFeedback)
		{
			var customerFb = _dbContext.CustomerFeedbacks.Find(customerFeedback.Ident);

			var mli = new MLInputFeedback
			{
				Sentiment = customerFeedback.ConfirmedSentiment,
				Language = customerFeedback.ConfirmedLanguage,
				Text = customerFb.Text
			};

			_dbContext.MLInputFeedbacks.Add(mli);
			customerFb.SentToML = true;
			customerFb.ConfirmedSentiment = customerFeedback.ConfirmedSentiment;
			customerFb.ConfirmedLanguage = customerFeedback.ConfirmedLanguage;
			_dbContext.SaveChanges();

			return true;
		}
	}
}
