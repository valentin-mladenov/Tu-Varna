using System;

namespace Shapes.Classes
{
    class Triangle : IShape
    {
        public double Aside { get; private set; }
        public double Bside { get; private set; }
        public double Cside { get; private set; }
        public double AngleAB { get; private set; }

        public Triangle(double a, double b, double angleAB)
        {
            if (a <= 0)
            {
                throw new ArgumentOutOfRangeException("A side be a positive number!");
            }
            if (b <= 0)
            {
                throw new ArgumentOutOfRangeException("B side be a positive number!");
            }
            if (0 < angleAB && angleAB < 90)
            {
                throw new ArgumentOutOfRangeException("Angle should be between 0 and 90!");
            }

            this.Aside = a;
            this.Bside = b;
            this.AngleAB = angleAB;
            this.Cside = Math.Sqrt((a * a) + (b * b) - (2 * a * b * Math.Cos(angleAB)));
        }

        public double CalculateSurface()
        {
            var semiperimeter = this.CalculatePerimeter() / 2;

            return Math.Sqrt(semiperimeter * (semiperimeter - this.Aside) * (semiperimeter - this.Bside) * (semiperimeter - this.Cside));
        }

        public double CalculatePerimeter()
        {
            return this.Cside + this.Bside + this.Aside;
        }

        public double LongestSide()
        {
            var compareAB = Math.Max(this.Aside, this.Bside);

            return Math.Max(compareAB, this.Cside);
        }

        public override string ToString()
        {
            return base.ToString();
        }
    }
}
