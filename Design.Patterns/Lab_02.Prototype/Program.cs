using System;

namespace lab._02.Prototype
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Hello World!");
        }
    }

    abstract class Prototype

    {
        public string Id { get; private set; }

        // Constructor

        public Prototype(string id)
        {
            this.Id = id;
        }

        public abstract Prototype Clone();
    }

    class ConcretePrototype1 : Prototype

    {
        // Constructor

        public ConcretePrototype1(string id)
          : base(id)
        {
        }

        public override Prototype Clone()
        {
            return (Prototype)this.MemberwiseClone();
        }
    }

    class ConcretePrototype2 : Prototype
    {
        public ConcretePrototype2(string id)
          : base(id)
        {
        }

        public override Prototype Clone()
        {
            return (Prototype)this.MemberwiseClone();
        }
    }
}
