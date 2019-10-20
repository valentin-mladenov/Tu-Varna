using System;
using System.Collections.Generic;
using System.Linq;
using Bank_System.Classes;

namespace Bank_System
{
    class Program
    {
        static void Main(string[] args)
        {
            var customers = new List<Customer>();
            var accounts = new List<Account>();

            FileReadWriter.ReadCustomersFromFile(customers, "../../customers.csv");

            FileReadWriter.ReadAccountsFromFile(accounts, customers, "../../accounts.csv");

            var montClacs = new List<KeyValuePair<int, decimal>>();
            var rand = new Random();

            accounts.ForEach(acc => {
                var months = rand.Next(0, 100);

                acc.DepositMoney(rand.Next(0, 10000));

                if (acc is DepositAccount)
                {
                    (acc as DepositAccount).WithdrawMoney(rand.Next(0, 15000));
                }

                montClacs.Add(new KeyValuePair<int, decimal>(months, acc.CalculateInterest(months)));
            });

            FileReadWriter.WriteAccountMonthCalculation(accounts, montClacs, "../../monthCalcs.txt");

            var interestAccounts = searchForAccountsWithInterest(15.3M, accounts);

            FileReadWriter.WriteAccountMonthCalculation(
                accounts.Where(acc => acc.Interest == 15.3M).ToList(),
                montClacs,
                "../../accountsWithCommonInterest.txt");

            accounts.Sort();

            FileReadWriter.WriteAccountMonthCalculation(
                accounts,
                montClacs,
                "../../accountsSorted.txt");

            FileReadWriter.WriteAccountMonthCalculation(
                accounts.OrderBy(acc => acc.Interest).ToList(),
                montClacs,
                "../../accountsOrderedByInterest.txt");

            FileReadWriter.WriteAccountMonthCalculation(
                accounts.OrderBy(acc => acc.Balance).ToList(),
                montClacs,
                "../../accountsOrderedByBalance.txt");

            Console.ReadKey();
        }

        static List<Account> searchForAccountsWithInterest(decimal interest, List<Account> accounts)
        {
            return accounts
                .Where(acc => acc.Interest == interest)
                .ToList();
        }
    }
}
