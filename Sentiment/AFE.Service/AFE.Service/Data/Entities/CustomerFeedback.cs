using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace AFE.Service.Data.Entities
{
	public class CustomerFeedback
	{
		[Key]
		public Guid Id { get; set; }
        public string UserName { get; set; }
		public string Text { get; set; }
		public bool Sentiment { get; set; }
		public bool ConfirmedSentiment { get; set; }
		public decimal Probability { get; set; }
		public decimal Score { get; set; }
		public bool SentToML { get; set; }
	}
}
