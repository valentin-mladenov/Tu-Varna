using System;

namespace Lab_12.Proxy
{
    class Program
    {
        static void Main(string[] args)
        {
            // Create math proxy
            MathProxy proxy = new MathProxy();

            // Do the math
            Console.WriteLine("4 + 2 = " + proxy.Addition(4, 2));
            Console.WriteLine("4 - 2 = " + proxy.Subtraction(4, 2));
            Console.WriteLine("4 * 2 = " + proxy.Multiplication(4, 2));
            Console.WriteLine("4 / 2 = " + proxy.Division(4, 2));
        }
    }
}
