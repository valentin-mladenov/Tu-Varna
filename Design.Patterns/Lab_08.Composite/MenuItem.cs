using System;

namespace Lab_07.Composite
{
    public class MenuItem : IMenuComponent
    {
        public string Name { get; private set; }

        public string Description { get; private set; }

        public double Price { get; private set; }

        public bool Vegetarian { get; private set; }

        public MenuItem(string name, string description, double price, bool isveg)
        {
            Name = name;
            Description = description;
            Price = price;
            Vegetarian = isveg;
        }

        public void Print()
        {
            Console.WriteLine($"{Name} : {Price}  {(Vegetarian ? "Вегетарианско" : string.Empty)} \n {Description}");
        }
    }
}