using Lab._01.Singleton.Pattern;
using System;

namespace Lab._01.Singleton
{
    public class Program
    {
        static void Main(string[] args)
        {
            try
            {
                var boiler1 = ChocolateBoiler.GetInstance();
                var boiler2 = ChocolateBoiler.GetInstance();

                if (boiler1 == boiler2)
                {
                    Console.WriteLine("Бойлер 1 и Бойлер 2 са един и същ!");
                }

                boiler1.Fill();
                Console.WriteLine("Статус на Бойлер 1: " + boiler1.BoilerStatus.ToString());
                boiler2.Boil();
                Console.WriteLine("Статус на Бойлер 2: " + boiler2.BoilerStatus.ToString());
                boiler1.Drain();
                Console.WriteLine("Статус на Бойлер 1: " + boiler1.BoilerStatus.ToString());

                // Wait for user
                Console.ReadKey();
            }
            catch (Exception)
            {
                Console.Write("Опаааа!!!");
            }
        }
    }
}
