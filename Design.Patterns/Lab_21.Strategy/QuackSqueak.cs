using System;

namespace Lab_21.Strategy
{
    class QuackSqueak : IQuackBehaviour
    {
        public void Quack()
        {
            Console.WriteLine("Скрееек");
        }
    }
}
