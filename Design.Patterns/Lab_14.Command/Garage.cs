using System;

namespace Lab_14.Command
{
    internal class Garage
    {
        private readonly string name;

        public Garage(string name)
        {
            this.name = name;
        }

        internal void Open()
        {
            Console.WriteLine($"{this.name}:\tГаражът е отворен");
        }

        internal void Close()
        {
            Console.WriteLine($"{this.name}:\tГаражът е затворен");
        }
    }
}