using System;

namespace Lab_06.Adapter
{
    public class TurkeyAdapter : IDuck
    {
        private readonly ITurkey turkey;

        public TurkeyAdapter(ITurkey turkey)
        {
            this.turkey = turkey;
        }

        public void Quack()
        {
            turkey.Gobble();
        }

        public void Fly()
        {
            for (var i = 0; i < 5; i++)
            {
                turkey.Fly();
                Console.WriteLine("Почива..");
            }
        }
    }
}