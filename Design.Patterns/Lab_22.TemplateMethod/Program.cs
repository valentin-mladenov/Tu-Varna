namespace Lab_22.TemplateMethod
{
    using System;
    using System.Collections.Generic;
    using Lab_22.TemplateMethod.Beverages;

    class Program
    {
        static void Main()
        {
            var bevareges = new MakeBeverege();

            var tea = new Tea();
            tea.HasCondiments = true;
            tea.Sugar = 5;

            bevareges.Prepare(tea);

            Console.WriteLine();

            var coffee = new Coffee();
            coffee.HasCondiments = false;
            tea.Sugar = 2;
            bevareges.Prepare(coffee);
        }
    }
}
