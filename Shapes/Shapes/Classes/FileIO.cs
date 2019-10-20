using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Text;

namespace Shapes.Classes
{
    public static class FileIO
    {
        public static void ReadShapesFromFile(List<IShape> shapes, string accountFileName)
        {
            var path = Path.Combine(Directory.GetCurrentDirectory(), accountFileName);

            var listStringLines = File.ReadLines(path).ToList();

            listStringLines.ForEach(l => {
                var listObj = l.Split(';').ToList();

                IShape shape;
                if (listObj[0] == typeof(Rectangle).Name)
                {
                    shape = new Rectangle(
                        double.Parse(listObj[1], CultureInfo.InvariantCulture),
                        double.Parse(listObj[2], CultureInfo.InvariantCulture));
                }
                else if (listObj[0] == typeof(Circle).Name)
                {
                    shape = new Circle(
                        double.Parse(listObj[1], CultureInfo.InvariantCulture));
                }
                else if (listObj[0] == typeof(Triangle).Name)
                {
                    shape = new Triangle(
                        double.Parse(listObj[1], CultureInfo.InvariantCulture),
                        double.Parse(listObj[2], CultureInfo.InvariantCulture),
                        double.Parse(listObj[3], CultureInfo.InvariantCulture));
                }
                else
                {
                    throw new ArgumentOutOfRangeException("Wrong Account type in file");
                }

                shapes.Add(shape);
            });
        }

        public static void WriteShapesAsStrings(List<IShape> shapes, string monthFileName)
        {
            var path = Path.Combine(Directory.GetCurrentDirectory(), monthFileName);

            var sb = new StringBuilder();

            shapes.ForEach(shape => {
                sb.AppendLine(shape.ToString());
            });

            File.WriteAllText(path, sb.ToString());
        }
    }
}
