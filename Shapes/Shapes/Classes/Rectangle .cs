using System;
using System.Globalization;
using System.Text;

namespace Shapes.Classes
{
    class Rectangle : IShape
    {
        public double Width { get; private set; }
        public double Height { get; private set; }

        public Rectangle(double width, double height)
        {
            if (width <= 0)
            {
                throw new ArgumentOutOfRangeException("Width should be a positive number!");
            }

            if (height <= 0)
            {
                throw new ArgumentOutOfRangeException("Height should be a positive number!");
            }

            this.Width = width;
            this.Height = height;
        }

        public double CalculateSurface()
        {
            return this.Width * this.Height;
        }

        public double CalculatePerimeter()
        {
            return this.Width * 2 + this.Height * 2;
        }

        public double LongestSide()
        {
            return Math.Max(this.Width, this.Height);
        }

        public override string ToString()
        {
            var sb = new StringBuilder();

            sb.Append("Rectangle with Width: ");
            sb.Append(Width.ToString("0.00", CultureInfo.InvariantCulture));
            sb.Append(", Height: ");
            sb.Append(Height.ToString("0.00", CultureInfo.InvariantCulture));
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
