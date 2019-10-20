using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Shapes.Classes
{
    public static class FileIO
    {
        public static void ReadAccountsFromFile(List<IShape> accounts, string accountFileName)
        {
            var path = Path.Combine(Directory.GetCurrentDirectory(), accountFileName);

            var listStringLines = File.ReadLines(path).ToList();

            listStringLines.ForEach(l => {
                var listObj = l.Split(';').ToList();

                IShape account;
                if (listObj[0] == typeof(Rectangle).Name)
                {
                    account = new Rectangle(
                        double.Parse(listObj[2], CultureInfo.InvariantCulture),
                        double.Parse(listObj[3], CultureInfo.InvariantCulture));
                }
                else if (listObj[0] == typeof(Circle).Name)
                {
                    account = new Circle(
                        double.Parse(listObj[1], CultureInfo.InvariantCulture));
                }
                else if (listObj[0] == typeof(Triangle).Name)
                {
                    account = new Triangle(
                        double.Parse(listObj[1], CultureInfo.InvariantCulture),
                        double.Parse(listObj[2], CultureInfo.InvariantCulture),
                        double.Parse(listObj[3], CultureInfo.InvariantCulture));
                }
                else
                {
                    throw new ArgumentOutOfRangeException("Wrong Account type in file");
                }

                accounts.Add(account);
            });
        }

        public static void WriteAccountMonthCalculation(
            List<IShape> accounts, List<KeyValuePair<int, decimal>> montClacs, string monthFileName)
        {
            var path = Path.Combine(Directory.GetCurrentDirectory(), monthFileName);

            var sb = new StringBuilder();
            var index = 0;
            accounts.ForEach(acc => {
                var strInterest = montClacs[index].Value.ToString("0.00", CultureInfo.InvariantCulture);
                var montcalc = $"Months: {montClacs[index].Key}, with CalculatedInterest: {strInterest} for ";
                sb.AppendLine(montcalc + acc.ToString());
                index++;
            });

            File.WriteAllText(path, sb.ToString());
        }
    }
}
