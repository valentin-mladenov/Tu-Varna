using Microsoft.ML.Data;

namespace AFE.Model.Fabric.Models
{
    public class LanguagePrediction
    {
        [ColumnName("PredictedLabel")]
        public bool Prediction { get; set; }

        public float Probability { get; set; }

        public float Score { get; set; }
    }
}
