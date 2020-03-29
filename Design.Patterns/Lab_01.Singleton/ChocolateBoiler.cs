using System;

namespace Lab._01.Singleton
{
    public class ChocolateBoiler
    {
        private static readonly Lazy<ChocolateBoiler> lazyInstance = new Lazy<ChocolateBoiler>(() => new ChocolateBoiler());

        public static ChocolateBoiler GetInstance() => lazyInstance.Value;

        public Status BoilerStatus;

        private ChocolateBoiler()
        {
            Console.WriteLine("Започване");
            this.BoilerStatus = Status.Empty;
        }

        public void Fill()
        {
            if (!IsEmpty) return;
            Console.WriteLine("Пълнене...");
            this.BoilerStatus = Status.InProgress;
        }

        public void Drain()
        {
            if (!IsBoiled) return;
            Console.WriteLine("Източване...");
            this.BoilerStatus = Status.Empty;
        }

        public void Boil()
        {
            if (IsBoiled || IsEmpty) return;
            Console.WriteLine("Кипене...");
            this.BoilerStatus = Status.Boiled;
        }

        private bool IsEmpty => (this.BoilerStatus == Status.Empty);

        private bool IsBoiled => (this.BoilerStatus == Status.Boiled);
    }

    public enum Status
    {
        Empty, InProgress, Boiled
    }
}
