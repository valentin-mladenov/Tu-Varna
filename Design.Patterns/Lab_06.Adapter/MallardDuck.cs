using System;

namespace Lab_06.Adapter
{
    class MallardDuck : IDuck
    {
        public void Quack()
        {
            Console.WriteLine("Кряк Кряк Кряк");
        }

        public void Fly()
        {
            Console.WriteLine("Прелита 500 мтера");
        }
    }
}
