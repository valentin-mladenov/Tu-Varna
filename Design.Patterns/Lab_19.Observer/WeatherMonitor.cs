using System;
using System.Collections.Generic;

namespace Lab_19.Observer
{
    sealed class WeatherMonitor : IObserver<Weather>
    {
        private IDisposable _cancellation;
        private readonly string _name;
        private readonly List<WeaterCharecteristic> options;

        public void Subscribe(WeatherSupplier provider)
        {
            _cancellation = provider.Subscribe(this);
        }

        public void Unsubscribe()
        {
            _cancellation.Dispose();
        }

        public WeatherMonitor(string name, List<WeaterCharecteristic> options)
        {
            _name = name;
            this.options = options;
        }

        public void OnError(Exception error)
        {
            Console.WriteLine(error.Message);
        }

        public void OnNext(Weather value)
        {
            Console.WriteLine(_name);

            if (options.Contains(WeaterCharecteristic.Temperature))
            {
                string op = $"| Температура : {value.Temperature} Целзий |";
                Console.Write(op);
                
            }
            if (options.Contains(WeaterCharecteristic.Pressure))
            {
                string op = $"| Налягане : {value.Pressure} bar |";
                Console.Write(op);
            }
            if (options.Contains(WeaterCharecteristic.Humidity))
            {
                string op = $"| ВЛажност : {value.Humidity*100} % |";
                Console.Write(op);
            }

            if (!(options.Contains(WeaterCharecteristic.Temperature) 
                || options.Contains(WeaterCharecteristic.Pressure) 
                || options.Contains(WeaterCharecteristic.Humidity)))
            {
                OnError(new Exception("Опааа... Грешка. Няма такава мерна еденица. "));
            }
            Console.WriteLine();
        }

        public void OnCompleted()
        {
            Console.WriteLine("Завърших работа");
        }
    }
}
