using System;
using System.Globalization;
using System.Text;

namespace Shapes.Classes
{
    class Triangle : IShape
    {
        public double Aside { get; private set; }
        public double Bside { get; private set; }
        public double Cside { get; private set; }
        public double AngleAB { get; private set; }
        public double AngleBC { get; private set; }
        public double AngleAC { get; private set; }

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
            if (angleAB < 0 || 90 < angleAB)
            {
                throw new ArgumentOutOfRangeException("Angle should be between 0 and 90!");
            }

            this.Aside = a;
            this.Bside = b;
            this.AngleAB = angleAB;

            this.Cside = Math.Sqrt((a * a) + (b * b) - (2 * a * b * Math.Cos(Math.PI * angleAB / 180.0)));

            this.AngleBC = (Math.Acos((b * b + Cside * Cside - a * a) / (2 * Cside * b)) * 180) / Math.PI;

            this.AngleAC = 180 - angleAB - AngleBC;
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
            var sb = new StringBuilder();

            sb.Append("Triangle with Side A: ");
            sb.Append(Aside.ToString("0.00", CultureInfo.InvariantCulture));
            sb.Append(", Side B: ");
            sb.Append(Bside.ToString("0.00", CultureInfo.InvariantCulture));
            sb.Append(", Side C: ");
            sb.Append(Cside.ToString("0.00", CultureInfo.InvariantCulture));
            sb.Append(", Angle AB: ");
            sb.Append(AngleAB.ToString("0.00", CultureInfo.InvariantCulture));
            sb.Append(", Angle BC: ");
            sb.Append(AngleBC.ToString("0.00", CultureInfo.InvariantCulture));
            sb.Append(", Angle AC: ");
            sb.Append(AngleAC.ToString("0.00", CultureInfo.InvariantCulture));
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
