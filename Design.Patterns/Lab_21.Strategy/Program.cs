using System;

namespace Lab_21.Strategy
{
    class Program
    {
        static void Main(string[] args)
        {
            var mallard = new MallardDuck();

            mallard.Quacker = new QuackNormal();
            mallard.Display();
            Console.WriteLine();

            mallard.Flyer = new FlyWings();
            mallard.Display();
            Console.WriteLine();

            mallard.Quacker = new QuackNope();
            mallard.Display();
        }
    }
}
