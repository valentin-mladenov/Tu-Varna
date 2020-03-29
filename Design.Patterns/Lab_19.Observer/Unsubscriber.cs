using System;
using System.Collections.Generic;

namespace Lab_19.Observer
{
    internal class Unsubscriber<TWeather> : IDisposable
    {
        private readonly List<IObserver<TWeather>> observers;
        private readonly IObserver<TWeather> observer;

        internal Unsubscriber(
            List<IObserver<TWeather>> observers, 
            IObserver<TWeather> observer)
        {
            this.observers = observers;
            this.observer = observer;
        }

        public void Dispose()
        {
            if (this.observers.Contains(this.observer))
            {
                this.observers.Remove(this.observer);
            }
        }
    }
}
