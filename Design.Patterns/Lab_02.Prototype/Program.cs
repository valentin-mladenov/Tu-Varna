using System;

namespace Lab_02.Prototype
{
    class Program
    {
        static void Main(string[] args)
        {
            ColorManager colormanager = new ColorManager();

            colormanager["червено"] = new Color(255, 0, 0);
            colormanager["зелено"] = new Color(0, 255, 0);
            colormanager["синьо"] = new Color(0, 0, 255);

            colormanager["гневно"] = new Color(255, 54, 0);
            colormanager["мирно"] = new Color(128, 211, 128);
            colormanager["пламък"] = new Color(211, 34, 20);

            Console.WriteLine(colormanager.ToString());

            Color color1 = colormanager["червено"].Clone() as Color;
            Color color2 = colormanager["мирно"].Clone() as Color;
            Color color3 = colormanager["пламък"].Clone() as Color;
            
            Console.WriteLine();
            Console.WriteLine(color1);
            Console.WriteLine(color2);
            Console.WriteLine(color3);
        }
    }
}
