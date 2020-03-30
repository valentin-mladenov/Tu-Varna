using System;

namespace Lab_21.Strategy
{
    class QuackNormal : IQuackBehaviour
    {
        public void Quack()
        {
            Console.WriteLine("Квак квак");
        }      
    }
}
