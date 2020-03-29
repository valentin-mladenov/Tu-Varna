using System;

namespace Lab_11.Flyweight
{
    public class CoconutMilkTea : IBeverage
    {
        public CoconutMilkTea()
        {
            Console.WriteLine("Създаване на чай с кокосово мляко");
        }
        
        public void Drink()
        {
            Console.WriteLine("Даммм... това е чай с кокосово мляко");
        }
    }
}