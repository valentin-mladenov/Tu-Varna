
namespace SentimentML.Model
{
    public static class Constants
    {
        public static string TrainDataURL { get; set; } = @"../SentimentML.Model/Data/train.data.tsv";
       // public static string TrainDataURL1 { get; set; } = @"../AFE.Model.Fabric/Data/all_words.tsv";

        public static string ModelFilePath { get; set; } = @"../../../../SentimentML.Model/MLModel.zip";

        // "C:\Users\hudso\source\repos\Tu-Varna\Sentiment\AFE.Service\AFE.Model.Fabric"

        public static string ConsumeModelURL { get; set; } = @"C:\Users\hudso\source\repos\Tu-Varna\Sentiment\Web.Service\SentimentML.Model\MLModel.zip";
    }
}
