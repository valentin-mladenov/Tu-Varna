using System;
using System.IO;

namespace ml.train
{
    class Program
    {
        static string dir = @"C:\Users\hudso\source\repos\Tu-Varna\Sentiment\AFE.Service\AFE.Model.Fabric\Data";

        static void Main(string[] args)
        {
            using (StreamWriter writer = File.CreateText(dir + @"\train.data.tsv"))
            {
                using (StreamReader reader1 = File.OpenText(dir + @"\train_data_en.tsv"))
                {
                    using (StreamReader reader2 = File.OpenText(dir + @"\all_words_bg-de-ru.tsv"))
                    {
                        string line1 = null;
                        string line2 = null;
                        while ((line1 = reader1.ReadLine()) != null)
                        {
                            writer.WriteLine(line1);
                            line2 = reader2.ReadLine();
                            if (line2 != null)
                            {
                                writer.WriteLine(line2);
                            }
                        }
                    }
                }
            }
        }
    }
}
