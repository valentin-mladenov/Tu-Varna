using System;
using System.Collections.Generic;
using System.Text;

namespace Lab_18.Memento
{
    /// <summary>
    /// The 'Originator' class
    /// </summary>
    class SalesProspect
    {
        public string Name { get; set; }

        public string Phone { get; set; }

        public double Budget { get; set; }

        public Memento SaveMemento()
        {
            return new Memento(Name, Phone, Budget);
        }

        public void RestoreMemento(Memento memento)
        {
            this.Name = memento.Name;
            this.Phone = memento.Phone;
            this.Budget = memento.Budget;
        }

        public override string ToString()
        {
            return $"Име: {Name}, Телефон: {Phone}, Бюджет: {Budget}";
        }
    }
}
