using System;

namespace Lab_11.Flyweight
{
    public class BubbleMilkTea : IBeverage
    {
        public BubbleMilkTea()
        {
            Console.WriteLine("Създаване на мехурчест чай с мляко");
        }

        public void Drink()
        {
            Console.WriteLine("Даммм... това е мехурчест чай с мляко");
        }
    }
}