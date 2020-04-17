// Приложение за произвеждане на три вида сладолед: Ванилов, Млечен и Шоколадов.
// Избрах шаблон Factory method

using System;

namespace Task_13_IceCreamMaker
{


    class Program
    {
        static void Main(string[] args)
        {
            var vanilla = IceCreamFactory.Create(IceCreamType.Vanilla);
            Console.WriteLine("Заповядай " + vanilla.Serve());

            var chocolate = IceCreamFactory.Create(IceCreamType.Chocolate);
            Console.WriteLine("Заповядай " + chocolate.Serve());

            var milk = IceCreamFactory.Create(IceCreamType.Milk);
            Console.WriteLine("Заповядай " + milk.Serve());
        }
    }
}
