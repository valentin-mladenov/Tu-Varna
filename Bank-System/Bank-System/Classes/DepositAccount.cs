using System;
using Bank_System.Interfaces;

namespace Bank_System.Classes
{
    class DepositAccount : Account, IWithdraw
    {
        public DepositAccount(Customer customer, decimal balance, decimal interest)
            : base(customer, balance, interest)
        { }

        public void WithdrawMoney(decimal withdraw)
        {
            if (base.Balance < withdraw)
            {
                throw new ArgumentException("You can't withdraw more than you have!?");
            }
            else
            {
                base.Balance -= withdraw;
            }
        }

        public override decimal CalculateInterest(int months)
        {
            if (base.Balance >= 1000)
            {
                return base.CalculateInterest(months);
            }
            else
            {
                return 0;
            }
        }

        public override string ToString()
        {
            return "DepositAccount with " + base.ToString();
        }
    }
}