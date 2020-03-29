using System;
using System.Collections;
using System.Collections.Generic;

namespace Lab_08.Composite
{
    public class Menu : IMenuComponent, IEnumerable
    {
        public string Name { get; private set; }
        public string Description { get; private set; }

        Dictionary<string, IMenuComponent> components = new Dictionary<string, IMenuComponent>();

        public Menu(string name, string description)
        {
            Name = name;
            Description = description;
        }

        public string GetName()
        {
            return this.Name;
        }

        public void Add(IMenuComponent component)
        {
            components[component.GetName()] = component;
        }

        public void Remove(IMenuComponent component)
        {
            components.Remove(component.GetName());
        }

        public IMenuComponent GetChild(string name)
        {
            return components[name];
        }

        public void Print()
        {
            Console.WriteLine(Name);
            Console.WriteLine("___________");
            foreach (var menuComponent in components.Values)
            {
                menuComponent.Print();
            }
            Console.WriteLine();
        }

        public IEnumerator GetEnumerator()
        {
            foreach (IMenuComponent child in components.Values)
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