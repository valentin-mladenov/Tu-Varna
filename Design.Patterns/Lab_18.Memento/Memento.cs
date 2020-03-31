using System;
using System.Collections.Generic;
using System.Text;

namespace Lab_18.Memento
{
    /// <summary>
    /// The 'Memento' class
    /// </summary>
    class Memento
    {
        public string Name { get; private set; }

        public string Phone { get; private set; }

        public double Budget { get; private set; }

        public Memento(string name, string phone, double budget)
        {
            this.Name = name;
            this.Phone = phone;
            this.Budget = budget;
        }
    }
}
