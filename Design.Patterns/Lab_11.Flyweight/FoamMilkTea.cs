using System;

namespace Lab_11.Flyweight
{
    public class FoamMilkTea : IBeverage
    {
        
        public FoamMilkTea()
        {
            Console.WriteLine("Създаване на чай с млечна пяна");
        }
        
        public void Drink()
        {
            Console.WriteLine("Дамм... това е чай с млечна пяна");
        }
    }
}