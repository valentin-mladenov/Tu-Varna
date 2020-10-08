using System;
using System.ComponentModel.DataAnnotations;


namespace AFE.Service.Data.Entities
{

	public class MLInputFeedback
	{
		[Key]
		public Guid Id { get; set; }
		public string Text { get; set; }
		public bool Sentiment { get; set; }
	}
}