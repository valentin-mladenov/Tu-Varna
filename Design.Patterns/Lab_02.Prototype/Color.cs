using System;

namespace Lab_02.Prototype
{
    /// <summary>
    /// The 'ConcretePrototype' class
    /// </summary>
    public class Color : IColorPrototype
    {
        private int red;
        private int green;
        private int blue;

        /// <summary>
        /// Constructor
        /// </summary>
        /// <param name="red"></param>
        /// <param name="green"></param>
        /// <param name="blue"></param>
        public Color(int red, int green, int blue)
        {
            this.red = red;
            this.green = green;
            this.blue = blue;
        }


        /// <summary>
        /// Create a shallow copy
        /// </summary>
        /// <returns></returns>
        public IColorPrototype Clone()
        {
            Console.WriteLine($"Клониране на цветове ЧЗС: {red,3}, {green,3}, {blue,3}");

            return this.MemberwiseClone() as IColorPrototype;
        }

        public override string ToString()
        {
            return $"Червено: {red,3}, Зелено: {green,3}, Синьо {blue,3}";
        }
    }
}
