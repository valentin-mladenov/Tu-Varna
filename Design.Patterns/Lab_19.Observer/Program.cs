using System;
using System.Collections.Generic;

namespace Lab_19.Observer
{
    class Program
    {
        static void Main()
        {
            var provider = new WeatherSupplier();
            var observer1 = new WeatherMonitor(
                "Температура и налягане",
                new List<WeaterCharecteristic> {
                    WeaterCharecteristic.Temperature,
                    WeaterCharecteristic.Pressure
                });

            var observer2 = new WeatherMonitor(
                "Влажност",
                new List<WeaterCharecteristic>
                {
                    WeaterCharecteristic.Humidity,
                });

            var observer3 = new WeatherMonitor(
                "Температура, влажност и налягане",
                new List<WeaterCharecteristic>
                {
                    WeaterCharecteristic.Humidity,
                    WeaterCharecteristic.Temperature,
                    WeaterCharecteristic.Pressure
                });

            observer1.Subscribe(provider);
            provider.WeatherConditions(new Weather(32.0, 0.05, 1.5));
            Console.WriteLine();

            observer1.Subscribe(provider);
            provider.WeatherConditions(new Weather(33.5, 0.04, 1.7));
            Console.WriteLine();

            observer2.Subscribe(provider);
            provider.WeatherConditions(new Weather(37.5, 0.07, 1.2));
            Console.WriteLine();
            observer1.Unsubscribe();
            observer2.Unsubscribe();

            observer3.Subscribe(provider);
            provider.WeatherConditions(new Weather(27.5, 0.7, 3.2));
        }
    }
}
