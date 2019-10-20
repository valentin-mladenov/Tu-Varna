using System;

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
            return base.ToString();
        }
    }
}
