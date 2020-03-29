using System;
using System.Collections.Generic;

namespace Lab_11.Flyweight
{
    public class BeverageFlyweightFactory
    {
        private readonly Dictionary<BeverageType, IBeverage> beverages;

        public BeverageFlyweightFactory()
        {
            this.beverages = new Dictionary<BeverageType, IBeverage>();
        }

        public IBeverage MakeBeverage(BeverageType type)
        {
            IBeverage beverage;
            this.beverages.TryGetValue(type, out beverage);
            if (beverage == null)
            {
                switch (type)
                {
                    case BeverageType.BubbleMilk:
                        beverage = new BubbleMilkTea();
                        this.beverages.Add(BeverageType.BubbleMilk, beverage);
                        break;
                    case BeverageType.FoamMilk:
                        beverage = new FoamMilkTea();
                        this.beverages.Add(BeverageType.FoamMilk, beverage);
                        break;
                    case BeverageType.OolongMilk:
                        beverage = new OolingMilkTea();
                        this.beverages.Add(BeverageType.OolongMilk, beverage);
                        break;
                    case BeverageType.CoconutMilk:
                        beverage = new CoconutMilkTea();
                        this.beverages.Add(BeverageType.CoconutMilk, beverage);
                        break;
                    default:
                        throw new ArgumentOutOfRangeException(nameof(type), type, null);
                }
            }

            return beverage;
        }
    }
}