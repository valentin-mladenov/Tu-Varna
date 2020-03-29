using System;

namespace Lab_14.Command
{
    public class Light
    {
        private readonly string name;

        public Light(string name)
        {
            this.name = name;
        }

        internal void On()
        {
            Console.WriteLine($"{this.name}:\tСветлини включени");
        }

        internal void Off()
        {
            Console.WriteLine($"{this.name}:\tСветлини изключени");
        }
    }
}