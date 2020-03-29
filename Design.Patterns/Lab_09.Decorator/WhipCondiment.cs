namespace Lab_08.Decorator
{
    class WhipCondiment : CondimentDecorator
    {
        Beverage _beverage;

        public WhipCondiment(Beverage beverage)
        {
            this._beverage = beverage;
        }

        public override string Description
        {
            get
            { 
                if (_beverage.Description.StartsWith("Късо"))
                {
                    return "Двойно " + _beverage.Description;
                }

                return "Късо " + _beverage.Description;
            }
        }

        public override double Cost()
        {
            return 0.15 + _beverage.Cost();
        }
    }
}
