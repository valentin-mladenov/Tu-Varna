using System;

namespace Lab_22.TemplateMethod
{
    class Tea : IBeverage
    {
        public int Sugar { get; set; }
        public bool HasCondiments { get; set; }

        public void Brew()
        {
            Console.WriteLine("Добавяне на чаени листа към водата и загряване");
        }

        public void AddCondiments()
        {
            Console.WriteLine($"Добавяне на лимон и {this.Sugar} лъжички захар");
        }

        public void Boil()
        {
            Console.WriteLine("Загряване на водата е чайник");
        }

        public void Pour()
        {
            Console.WriteLine("Сипване в чаена чаша");
        }

        public bool WantsCondiments()
        {
            return this.HasCondiments;
        }
    }
}