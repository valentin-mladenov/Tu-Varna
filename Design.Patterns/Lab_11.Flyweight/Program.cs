using System;

namespace Lab_11.Flyweight
{
    class Program
    {
        static void Main(string[] args)
        {
            var teaShop = new BubbleTeaShop();

            teaShop.Enumerate();
        }
    }
}
