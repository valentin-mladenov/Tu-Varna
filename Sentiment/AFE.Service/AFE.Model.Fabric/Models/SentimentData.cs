using Microsoft.ML.Data;

namespace AFE.Model.Fabric.Models
{
    public class SentimentData
    {
        [LoadColumn(0), ColumnName("Sentiment")]
        public bool Sentiment;

        [ColumnName("SentimentText"), LoadColumn(1)]
        public string SentimentText;
    }
}
