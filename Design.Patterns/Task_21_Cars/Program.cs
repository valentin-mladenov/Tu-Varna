// Decorator
// Да се създаде приложение Коли, което предлага три модела коли: економичен,
// среден и луксозен.Програмата трябва да може да предлага към всеки модел
// различни опции: волан, тапицерия, цвят и климатизация.

using System;
using Task_21_Cars.Accessories;

namespace Task_21_Cars
{
    class Program
    {
        static void Main(string[] args)
        {
            ICar economyCar = new EconomyCar();

            ICar basicEconomyCar = new BasicAccessories(economyCar);

            ICar advancedEconomyCar = new AdvancedAccessories(basicEconomyCar);

            Console.WriteLine("Детайли: " + advancedEconomyCar.GetDescription());
            Console.WriteLine("Волан: " + advancedEconomyCar.GetSteeringWheel());
            Console.WriteLine("Тапицерия: " + advancedEconomyCar.GetUpholstery());
            Console.WriteLine("Климатик: " + advancedEconomyCar.GetConditioning());
            Console.WriteLine("Цвят: " + advancedEconomyCar.GetColor());
            Console.WriteLine("Пълна цена: " + advancedEconomyCar.GetCost());

            ICar sportEconomyCar = new SportsAccessories(advancedEconomyCar);

            Console.WriteLine("Детайли: " + sportEconomyCar.GetDescription());
            Console.WriteLine("Волан: " + sportEconomyCar.GetSteeringWheel());
            Console.WriteLine("Тапицерия: " + sportEconomyCar.GetUpholstery());
            Console.WriteLine("Климатик: " + sportEconomyCar.GetConditioning());
            Console.WriteLine("Цвят: " + sportEconomyCar.GetColor());
            Console.WriteLine("Пълна цена: " + sportEconomyCar.GetCost());
        }
    }
}
