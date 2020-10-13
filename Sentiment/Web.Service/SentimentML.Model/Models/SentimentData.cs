using Microsoft.ML.Data;

namespace SentimentML.Model.Models
{
    public class SentimentData
    {
        [ColumnName("Sentiment"), LoadColumn(0)]
        public bool Sentiment;

        [ColumnName("SentimentText"), LoadColumn(1)]
        public string SentimentText;
    }
}
