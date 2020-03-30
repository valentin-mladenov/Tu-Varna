using System;

namespace Lab_22.TemplateMethod
{
    class Coffee : IBeverage
    {
        public int Sugar { get; set; }

        public bool HasCondiments { private get; set; }

        public void Brew()
        {
            Console.WriteLine("Добавяне на смляно кафе към водата и затопляне");
        }

        public void AddCondiments()
        {
            Console.WriteLine($"Добавяне на мляко и {this.Sugar} лъжички захар");
        }

        public void Boil()
        {
            Console.WriteLine("Загряване на водата в джезве");
        }

        public void Pour()
        {
            Console.WriteLine("Сипване в кафена чаша");
        }

        public bool WantsCondiments()
        {
            return this.HasCondiments;
        }
    }
}
