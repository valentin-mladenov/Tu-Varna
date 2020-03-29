namespace Lab_04.Factory.Pizza
{
    using System;

    abstract class AbstractPizza
    {
        public string Color;
        public string Name { protected get; set; }

        internal void Bake()
        {
            Console.WriteLine("Пече се на 135 градус Целзий за 20 минути");
        }

        internal void Cut()
        {
            Console.WriteLine("Разрязана е на диагонални парчета");
        }

        internal void Box()
        {
            Console.WriteLine($"Пицата е сложена в {Color} кутия!");
        }

        internal virtual void Prepare()
        {
            Console.WriteLine($"Приготвяне на {Name} с продукти:");
        }
    }
}
