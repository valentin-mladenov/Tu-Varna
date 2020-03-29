using System;

namespace Lab_04.Factory
{
    class Program
    {
        static void Main(string[] args)
        {
            // Създаването на пици е чрез Фактори метод
            // Съставките на пиците се добавят чрез Абстрактно фактори
            // Проектът има референция към Lab_05.Abstract.Factory (Class Library)
            Console.WriteLine("Софиянски поръчки:");
            Console.WriteLine();

            var sofiq1 = new DominosPizzaFactory();
            sofiq1.Order("Зверска");
            Console.WriteLine();

            var sofiq2 = new DominosPizzaFactory();
            sofiq2.Order("Вегитарианска");
            Console.WriteLine();

            Console.WriteLine("--------------------------");
            Console.WriteLine();
            Console.WriteLine("Варненски поръчки:");
            Console.WriteLine();

            var varna1 = new GodzillaPizzaFactory();
            varna1.Order("Вегитарианска");
            Console.WriteLine();

            var varna2 = new GodzillaPizzaFactory();
            varna2.Order("Четири сирена");
            Console.WriteLine();
        }
    }
}
