using System;

namespace Lab_21.Strategy
{
    class FlyNope : IFlyBehaviour
    {
        public void Fly()
        {
            Console.WriteLine("Не ме занимавай");
        }
    }
}
