using System;

namespace Lab_21.Strategy
{
    class FlyWings : IFlyBehaviour
    {
        public void Fly()
        {
            Console.WriteLine("Пляс пляс");
        }
    }
}
