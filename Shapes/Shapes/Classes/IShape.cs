using System;

namespace Shapes.Classes
{
    public interface IShape : IComparable<IShape>
    {
        double CalculateSurface();

        double CalculatePerimeter();

        double LongestSide();
    }
}