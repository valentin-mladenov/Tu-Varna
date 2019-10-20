using System;

namespace Bank_System.Classes
{
    public abstract class Customer : IComparable<Customer>
    {
        private string name;

        public string Name
        {
            get { return this.name; }
            private set
            {
                if (string.IsNullOrWhiteSpace(value))
                {
                    throw new ArgumentNullException("Name must have at least 1 character.");
                }
                else
                {
                    this.name = value;
                }
            }
        }

        protected Customer(string name)
        {
            this.Name = name;
        }

        public override string ToString()
        {
            return ", Name: " + this.Name;
        }

        public int CompareTo(Customer other)
        {
            return this.Name.CompareTo(other.Name);
        }
    }
}