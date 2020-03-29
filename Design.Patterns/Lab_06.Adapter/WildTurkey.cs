using System;

namespace Lab_06.Adapter
{
    class WildTurkey : ITurkey
    {
        public void Gobble()
        {
            Console.WriteLine("Гулю Гулю Гулю");
        }

        public void Fly()
        {
            Console.WriteLine("Прелита 100 метра");
        }
    }
}
