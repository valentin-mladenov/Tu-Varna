using System;
using Bank_System.Interfaces;

namespace Bank_System.Classes
{
    class LoanAccount: Account
    {
        const byte monthCompanyDiscount = 2;
        const byte monthIndividualDiscount = 3;
        const decimal noInterest = 0;

        public LoanAccount(Customer customer, decimal balance, decimal interest)
            : base(customer, balance, interest)
        {

        }

        public override decimal CalculateInterest(int months)
        {
            if (this.Customer is Company)
            {
                if (months <= monthCompanyDiscount)
                {
                    return noInterest;
                }

                months -= monthCompanyDiscount;

                return base.CalculateInterest(months);
            }
            else if (this.Customer is Individual)
            {
                if (months <= monthIndividualDiscount)
                {
                    return noInterest;
                }

                months -= monthIndividualDiscount;

                return base.CalculateInterest(months);
            }
            else
            {
                throw new ArgumentOutOfRangeException("Unsupported class");
            }
        }

        public override string ToString()
        {
            return "LoanAccount with " + base.ToString();
        }
    }
}