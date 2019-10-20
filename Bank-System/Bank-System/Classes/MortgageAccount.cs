using System;

namespace Bank_System.Classes
{
    class MortgageAccount : Account
    {
        const byte monthCompanyDiscount = 12;
        const byte monthIndividualDiscount = 6;
        const decimal noInterest = 0;

        public MortgageAccount(Customer customer, decimal balance, decimal interest) 
            : base(customer, balance, interest)
        { }
 
        public override decimal CalculateInterest(int months)
        {
            if (this.Customer is Individual)
            {
                if (months <= monthIndividualDiscount)
                {
                    return noInterest;
                }

                months -= monthIndividualDiscount;

                return base.CalculateInterest(months);
            }
            else if (this.Customer is Company)
            {
                if (months <= monthCompanyDiscount)
                {
                    return base.CalculateInterest(months)/2;
                }

                months -= monthCompanyDiscount;

                return base.CalculateInterest(6) + base.CalculateInterest(months);
            }
            else
            {
                throw new ArgumentOutOfRangeException("Unsupported class");
            }
        }

        public override string ToString()
        {
            return "MortgageAccount with " + base.ToString();
        }
    }
}
