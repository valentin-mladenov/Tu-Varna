using System;

namespace Lab_21.Strategy
{
    internal class QuackNope : IQuackBehaviour
    {
        public void Quack()
        {
            Console.WriteLine("...");
        }
    }
}