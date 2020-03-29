using System;
using System.Collections;
using System.Collections.Generic;

namespace Lab_07.Composite
{
    public class Menu : IMenuComponent, IEnumerable
    {
        public string Name { get; private set; }
        public string Description { get; private set; }

        List<IMenuComponent> components = new List<IMenuComponent>();

        public Menu(string name, string description)
        {
            Name = name;
            Description = description;
        }

        public void Add(IMenuComponent component)
        {
            components.Add(component);
        }

        public void Remove(IMenuComponent component)
        {
            components.Remove(component);
        }

        public IMenuComponent GetChild(int i)
        {
            return components[i];
        }

        public void Print()
        {
            Console.WriteLine(Name);
            Console.WriteLine("___________");
            foreach (var menuComponent in components)
            {
                menuComponent.Print();
            }
            Console.WriteLine();
        }

        public IEnumerator GetEnumerator()
        {
            foreach (IMenuComponent child in components)
            {
                yield return child;
            }
        }

        IEnumerator IEnumerable.GetEnumerator()
        {
            return GetEnumerator();
        }
    }
}