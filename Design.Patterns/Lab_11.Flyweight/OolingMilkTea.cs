using System;

namespace Lab_11.Flyweight
{
    public class OolingMilkTea: IBeverage
    {

        public OolingMilkTea()
        {
            Console.WriteLine("Създаване на Улунг чай с мляко");
        }

        public void Drink()
        {
            Console.WriteLine("Даммм... това е Улунг чай с мляко");
        }
    }
}