using AFE.Service.Data.Entities;
using AFE.Service.Data.Repositories.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;

namespace AFE.Service.Data.Repositories
{
	public class MLInputFBRepository : IMLInputFBRepository
	{
		private AFEContext _dbContext = null;


		public MLInputFBRepository(AFEContext context)
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


		public bool ConfirmFeeback(Guid customerFeedbackId, bool sentiment)
		{
			var customerFb = _dbContext.CustomerFeedbacks.Find(customerFeedbackId);

			var mli = new MLInputFeedback
			{
				Sentiment = customerFb.Sentiment,
				Text = customerFb.Text
			};

			_dbContext.MLInputFeedbacks.Add(mli);
			customerFb.SentToML = true;
			customerFb.ConfirmedSentiment = sentiment;
			_dbContext.SaveChanges();

			return true;
		}
	}
}
