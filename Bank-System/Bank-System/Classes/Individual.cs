using System;
using System.Text;

namespace Bank_System.Classes
{
    class Individual : Customer
    {
        private int age;
        private string egn;
        private bool sex;

        public bool Sex { get; private set; }

        public int Age
        {
            get { return this.age; }
            private set
            {
                if (value <= 0 || value > 120)
                {
                    throw new ArgumentOutOfRangeException("Incorrect age.");
                }
                else
                {
                    this.age = value;
                }
            }
        }

        public string EGN
        {
            get { return this.egn; }
            private set
            {
                if (string.IsNullOrWhiteSpace(value))
                {
                    throw new ArgumentNullException("EGN can't be empty.");
                }
                else if (value.Length != 10)
                {
                    throw new ArgumentOutOfRangeException("EGN has exactly 10 digits.");
                }
                else
                {
                    long temp = long.Parse(value); // Exeption trowing.
                    this.egn = value;
                }
            }
        }

        public Individual(string name, byte age, string egn, bool sex) :
            base(name)
        {
            this.Age = age;
            this.EGN = egn;
            this.Sex = sex;
        }

        public override string ToString()
        {
            var sb = new StringBuilder();

            sb.Append("Individual with Age: ");
            sb.Append(Age);
            sb.Append(", EGN: ");
            sb.Append(EGN);
            sb.Append(", Sex: ");
            sb.Append(this.Sex ? "Male" : "Female");

            return sb.ToString() + base.ToString();
        }
    }
}