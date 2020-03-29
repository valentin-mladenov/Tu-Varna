using System;

namespace Lab_09.Facade
{
    public class DvdPlayer
    {
        private Dvd dvd;
        private int time = 0;
        public void On() => Console.WriteLine("ДВД усреойстворо е включено");

        public void Insert(Dvd dvd)
        {
            this.dvd = dvd;
            Console.WriteLine($"Вкарване на диск: {dvd.Movie}");
            
        }

        public void Play() => Console.WriteLine($"Пускане на: {this.dvd.Movie}");

        public void Pause()
        {
            Console.WriteLine($"Пауза на: {this.time = (new Random()).Next(this.time, this.time + 120)}");
        }

        public void Resume()
        {
            Console.WriteLine($"Продължаване от: {this.time}");
        }

        public void Stop()
        {
            this.time = 0;
            Console.WriteLine($"Спиране");
        }
    }
}