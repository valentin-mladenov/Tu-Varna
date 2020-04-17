// Приложение Температурен конвертор, за преобразуване от Целзий във Фаренхайт.
// Избран Sngleton

using System;

namespace Task_12_TemperatureConverter
{
    class Program
    {
        static void Main()
        {
            Console.Write("Вкарай температура в целзий: ");
            var input = Console.ReadLine();

            var temp = double.Parse(input);

            var tempConverter = TemperatureConveter.GetInstance();

            var fahrenheit = tempConverter.ConvertCelsiusToFahrenheit(temp);
            Console.WriteLine("Преобразувано във Фаренхайт: " + fahrenheit);

            var celsius = tempConverter.ConvertFahrenheitToCelsius(fahrenheit);
            Console.WriteLine("Преобразувано обратно в Целзий: " + celsius);
        }
    }
}
