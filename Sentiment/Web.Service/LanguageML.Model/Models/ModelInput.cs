using Microsoft.ML.Data;

namespace LanguageML.Model.Models
{
    public class ModelInput
    {
        [ColumnName("Language"), LoadColumn(0)]
        public string Language { get; set; }


        [ColumnName("Text"), LoadColumn(1)]
        public string Text { get; set; }


    }
}
