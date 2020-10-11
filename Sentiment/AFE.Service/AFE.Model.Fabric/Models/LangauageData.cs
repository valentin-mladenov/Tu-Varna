using Microsoft.ML.Data;

namespace AFE.Model.Fabric.Models
{
    public class LanguageData
    {
        [LoadColumn(0), ColumnName("Lang")]
        public string Language;

        [LoadColumn(1), ColumnName("Text")]
        public string Text;
    }
}
