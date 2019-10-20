using System;
using System.Globalization;
using System.Text;

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
            var sb = new StringBuilder();

            sb.Append("Circle with Radius: ");
            sb.Append(Radius.ToString("0.00", CultureInfo.InvariantCulture));
            sb.Append(", Surface: ");
            sb.Append(CalculateSurface().ToString("0.00", CultureInfo.InvariantCulture));
            sb.Append(" and Perimeter: ");
            sb.Append(CalculatePerimeter().ToString("0.00", CultureInfo.InvariantCulture));

            return sb.ToString();
        }

        public int CompareTo(IShape other)
        {
            var perimeterCheck = Math.Round(this.CalculatePerimeter(), 2).CompareTo(Math.Round(other.CalculatePerimeter(), 2));

            return perimeterCheck == 0
                ? Math.Round(this.CalculateSurface(), 2).CompareTo(Math.Round(other.CalculateSurface(), 2))
                : perimeterCheck;
        }
    }
}
