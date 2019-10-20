using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Text;
using Bank_System.Classes;

namespace Bank_System
{
    public static class FileReadWriter
    {
        public static void ReadCustomersFromFile(List<Customer> customers, string customerFileName)
        {
            var path = Path.Combine(Directory.GetCurrentDirectory(), customerFileName);

            var listStringLines = File.ReadLines(path).ToList();

            listStringLines.ForEach(l => {
                var listObj = l.Split(';').ToList();

                Customer customer;
                if (listObj[0] == typeof(Individual).Name)
                {
                    customer = new Individual(listObj[1], byte.Parse(listObj[2]), listObj[3], bool.Parse(listObj[4]));
                }
                else if (listObj[0] == typeof(Company).Name)
                {
                    customer = new Company(listObj[1], listObj[2]);
                }
                else
                {
                    throw new ArgumentOutOfRangeException("Wrong Customer type in file");
                }

                customers.Add(customer);
            });
        }

        public static void ReadAccountsFromFile(List<Account> accounts, List<Customer> customers, string accountFileName)
        {
            var path = Path.Combine(Directory.GetCurrentDirectory(), accountFileName);

            var listStringLines = File.ReadLines(path).ToList();

            listStringLines.ForEach(l => {
                var listObj = l.Split(';').ToList();

                Account account;
                if (listObj[0] == typeof(MortgageAccount).Name)
                {
                    account = new MortgageAccount(
                        customers[int.Parse(listObj[1])],
                        decimal.Parse(listObj[2], CultureInfo.InvariantCulture),
                        decimal.Parse(listObj[3], CultureInfo.InvariantCulture));
                }
                else if (listObj[0] == typeof(DepositAccount).Name)
                {
                    var intiger = int.Parse(listObj[1]);
                    var decim = decimal.Parse(listObj[2]);
                    var intigerm = decimal.Parse(listObj[3], CultureInfo.InvariantCulture);

                    account = new DepositAccount(
                        customers[intiger],
                        decimal.Parse(listObj[2], CultureInfo.InvariantCulture),
                        decimal.Parse(listObj[3], CultureInfo.InvariantCulture));
                }
                else if (listObj[0] == typeof(LoanAccount).Name)
                {
                    account = new LoanAccount(
                        customers[int.Parse(listObj[1])],
                        decimal.Parse(listObj[2], CultureInfo.InvariantCulture),
                        decimal.Parse(listObj[3], CultureInfo.InvariantCulture));
                }
                else
                {
                    throw new ArgumentOutOfRangeException("Wrong Account type in file");
                }

                accounts.Add(account);
            });
        }

        public static void WriteAccountMonthCalculation(
            List<Account> accounts, List<KeyValuePair<int, decimal>> montClacs, string monthFileName)
        {
            var path = Path.Combine(Directory.GetCurrentDirectory(), monthFileName);

            var sb = new StringBuilder();
            var index = 0;
            accounts.ForEach(acc => {
                var strInterest = montClacs[index].Value.ToString("0.00", CultureInfo.InvariantCulture);
                var montcalc= $"Months: {montClacs[index].Key}, with CalculatedInterest: {strInterest} for ";
                sb.AppendLine(montcalc + acc.ToString());
                index++;
            });

            File.WriteAllText(path, sb.ToString());
        }
    }
}
