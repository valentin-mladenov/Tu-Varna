using System;
using System.Collections.Generic;

namespace Lab_19.Observer
{
    class WeatherSupplier : IObservable<Weather>
    {
        private readonly List<IObserver<Weather>> observers;
        private List<Weather> Screens { get; }

        private List<Weather> GetScreens()
        {
            return Screens;
        }

        public WeatherSupplier()
        {
            observers = new List<IObserver<Weather>>();
            Screens = new List<Weather>();
        }

        public IDisposable Subscribe(IObserver<Weather> observer)
        {
            if (!observers.Contains(observer))
            {
                observers.Add(observer);

                foreach (var item in GetScreens())
                {
                    observer.OnNext(item);
                }
            }
            return new Unsubscriber<Weather>(observers, observer);
        }

        public void WeatherConditions(Weather weather)
        {
            foreach (var item in observers)
            {
                item.OnNext(weather);
            }
        }
    }
}
