namespace Lab_08.Decorator
{
    class MochaCondiment : CondimentDecorator
    {
        Beverage _beverage;

        public MochaCondiment(Beverage beverage)
        {
            this._beverage = beverage;
        }

        public override string Description {
           get
            {
                if (_beverage.Description.StartsWith("Моча"))
                {
                    return "Двойно " + _beverage.Description;
                }

                return "Моча " + _beverage.Description;
            }
        }

        public override double Cost()
        {
            return 0.2 + _beverage.Cost();
        }
    }
}
