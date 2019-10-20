using System;
using System.Globalization;
using System.Reflection;
using System.Text;
using Bank_System.Interfaces;

namespace Bank_System.Classes
{
    public abstract class Account : IDepositable, IComparable<Account>
    {
        public Customer Customer { get; set; }

        public decimal Balance { get; set; }

        public decimal Interest { get ; set; }

        public Account(Customer customer, decimal balance, decimal interest)
        {
            this.Customer = customer;
            this.Balance = balance;
            this.Interest = interest;
        }

        public virtual decimal CalculateInterest(int months)
        {
            return months * this.Interest;
        }

        public void DepositMoney(decimal money)
        {
            if(money <= 0)
            {
                throw new ArgumentOutOfRangeException("Cannot DEPOSIT a nedative or zero amount!!!");
            }

            this.Balance += money;
        }

        public override string ToString()
        {
            var sb = new StringBuilder();

            sb.Append("Balance: ");
            sb.Append(Balance.ToString("0.00", CultureInfo.InvariantCulture));
            sb.Append(", Interest: ");
            sb.Append(Interest.ToString("0.00", CultureInfo.InvariantCulture));
            sb.Append(", Customer: ");
            sb.Append(Customer.ToString());

            return sb.ToString();
        }

        public int CompareTo(Account other)
        {
            var customerCompare = this.Customer.CompareTo(other.Customer);

            return customerCompare == 0
                ? (
                    this.Balance.CompareTo(other.Balance) == 0 
                        ? this.Interest.CompareTo(other.Interest)
                        : this.Balance.CompareTo(other.Balance)
                )
                : customerCompare;
        }
    }
}