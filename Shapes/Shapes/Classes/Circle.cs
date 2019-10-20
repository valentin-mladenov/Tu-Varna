using System;

namespace Shapes.Classes
{
    class Circle : IShape
    {
        public double Radius { get; private set; }

        public Circle(double radius)
        {
            if (radius <= 0)
            {
                throw new ArgumentOutOfRangeException("Radius should be a positive number!");
            }
            else
            {
                this.Radius = radius;
            }
        }

        public double CalculateSurface()
        {
            return this.Radius * this.Radius * Math.PI;
        }

        public double CalculatePerimeter()
        {
            return this.Radius * 2 * Math.PI;
        }

        public double LongestSide()
        {
            return this.Radius;
        }

        public override string ToString()
        {
            return base.ToString();
        }
    }
}
