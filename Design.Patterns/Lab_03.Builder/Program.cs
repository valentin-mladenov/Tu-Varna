using System;

namespace Lab_03.Builder
{
    class Program
    {
        static void Main(string[] args)
        {
            var builder = new MaleHamburgerBuilder();
            var cook = new Cook(builder);
            var maleHamburger = cook.Build();

            cook.ChangeBuilder(new FemaleHamburgerBuilder());
            var femaleHamburger = cook.Build();

            Console.WriteLine($"Мъжки хамбургер: {maleHamburger}");
            Console.WriteLine($"Женски хамбургер: {femaleHamburger}");
        }
    }
}
