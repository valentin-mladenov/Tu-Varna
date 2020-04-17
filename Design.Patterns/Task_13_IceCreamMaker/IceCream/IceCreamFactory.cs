using System;

namespace Task_13_IceCreamMaker
{
    static class IceCreamFactory
    {
        public static IIceCream Create(IceCreamType type)
        {
            switch (type)
            {
                case IceCreamType.Chocolate:
                    return new ChocolateIceCream();
                case IceCreamType.Vanilla:
                    return new VanillaIceCream();
                case IceCreamType.Milk:
                    return new MilkIceCream();
                default:
                    throw new ArgumentException("Няма такъв сладолед");
            }
        }
    }
}
