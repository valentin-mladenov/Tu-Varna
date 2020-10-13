using System;
using System.ComponentModel.DataAnnotations;

namespace SentimentWeb.Service.Data.Entities
{
	public class MLInputFeedback
	{
		[Key]
		public long Id { get; set; }
		public string Text { get; set; }
		public bool Sentiment { get; set; }
		public string Language{ get; set; }
	}
}