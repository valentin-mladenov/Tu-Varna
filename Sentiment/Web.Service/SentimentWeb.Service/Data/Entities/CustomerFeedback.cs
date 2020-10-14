using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Text.Json;
using System.Text.Json.Serialization;

namespace SentimentWeb.Service.Data.Entities
{
	public class CustomerFeedback
	{
		[Key]
		public long Ident { get; set; }
		public Sex Sex { get; set; }
		public MaritalStatus MaritalStatus { get; set; }
		public AgeRange AgeRange { get; set; }
		public string Text { get; set; }
		public string Language { get; set; }
		public string InternalLangScore { get; set; }

		[NotMapped]
		public LanguageScore LanguageScore
		{
			get
			{
				return JsonSerializer.Deserialize<LanguageScore>(InternalLangScore);
			}
			set
			{
				InternalLangScore = JsonSerializer.Serialize(value);
			}
		}
		public string ConfirmedLanguage { get; set; }
		public bool Sentiment { get; set; }
		public decimal SentimentProbability { get; set; }
		public decimal SentimentScore { get; set; }
		public bool ConfirmedSentiment { get; set; }
		public bool SentToML { get; set; }
	}
}
