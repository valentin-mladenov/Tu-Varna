using Shapes.Classes;
using System;
using System.Collections.Generic;
using System.Linq;

namespace Shapes
{
    class Program
    {
        static void Main(string[] args)
        {
            var shapes = new List<IShape>();

            FileIO.ReadShapesFromFile(shapes, "../../files/shapes.csv");

            FileIO.WriteShapesAsStrings(shapes, "../../files/shapesAsStrings.txt");

            shapes.Sort();

            FileIO.WriteShapesAsStrings(shapes, "../../files/shapesSorted.txt");

            FileIO.WriteShapesAsStrings(
                shapes.Where(sh => sh.CalculatePerimeter() == 345.58).ToList(),
                "../../files/shapesWIthCommonPerimeter.txt");
            
            FileIO.WriteShapesAsStrings(
                shapes.OrderBy(sh => sh.CalculatePerimeter()).ToList(),
                "../../files/shapesOrderedByPerimeter.txt");

            FileIO.WriteShapesAsStrings(
                shapes.OrderBy(sh => sh.CalculateSurface()).ToList(),
                "../../files/shapesOrderedBySurface.txt");

            FileIO.WriteShapesAsStrings(
                shapes.OrderBy(sh => sh.LongestSide()).ToList(),
                "../../files/shapesOrderedByLongestSide.txt");

            Console.ReadKey();
        }
    }
}
