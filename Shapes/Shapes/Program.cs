using Shapes.Classes;
using System;

namespace Shapes
{
    class Program
    {
        static void Main(string[] args)
        {
            // The code provided will print ‘Hello World’ to the console.
            // Press Ctrl+F5 (or go to Debug > Start Without Debugging) to run your app.
            Console.WriteLine("Hello World!");

            IShape[] shapes = new IShape[]
            {
                new Triangle(2.3, 5.6, 60),
                new Rectangle(4.6, 6.2),
                new Circle(6.3)
            };

            foreach (var item in shapes)
            {
                Console.Write(item.GetType().Name + " with surface: ");
                Console.WriteLine(Math.Round(item.CalculateSurface(), 2));
            }

            Console.ReadKey();

            // Go to http://aka.ms/dotnet-get-started-console to continue learning how to build a console app! 
        }
    }
}
