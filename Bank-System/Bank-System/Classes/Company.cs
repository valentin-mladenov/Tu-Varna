using System;
using System.Text;

namespace Bank_System.Classes
{
    class Company : Customer
    {
        private string bulstat;

        public string Bulstat
        {
            get { return this.bulstat; }
            private set
            {
                if (value == null || string.IsNullOrWhiteSpace(value))
                {
                    throw new ArgumentNullException("Bulstat number can't be empty.");
                }
                else if (value.Length > 15 || value.Length < 13)
                {
                    throw new ArgumentOutOfRangeException("Bulstat number is between 13 and 15 digits.");
                }
                else
                {
                    long temp = long.Parse(value); // Exeption trowing.
                    this.bulstat = value;
                }
            }
        }

        public Company(string name, string bulstat)
            :base(name)
        {
            this.Bulstat = bulstat;
        }

        public override string ToString()
        {
            var sb = new StringBuilder();

            sb.Append("Company with Bulstat: ");
            sb.Append(Bulstat);

            return sb.ToString() + base.ToString();
        }
    }
}