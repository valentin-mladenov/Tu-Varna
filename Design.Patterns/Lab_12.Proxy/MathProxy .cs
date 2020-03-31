using System;
using System.Collections.Generic;
using System.Text;

namespace Lab_12.Proxy
{
    public class MathProxy : IMath
    {
        private Math math = new Math();

        public decimal Addition(decimal x, decimal y)
        {
            return this.math.Addition(x, y);
        }

        public decimal Subtraction(decimal x, decimal y)
        {
            return this.math.Subtraction(x, y);
        }

        public decimal Multiplication(decimal x, decimal y)
        {
            return this.math.Multiplication(x, y);
        }

        public decimal Division(decimal x, decimal y)
        {
            return this.math.Division(x, y);
        }
    }
}
