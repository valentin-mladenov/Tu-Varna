namespace Task_21_Cars.Accessories
{
    /// <summary>
    /// Basic Accessories Decorator
    /// </summary>
    public class BasicAccessories : CarAccessoriesDecorator
    {
        public BasicAccessories(ICar car)
        : base(car)
        {

        }

        public override string GetDescription()
        {
            return base.GetDescription() + ", Пакет основни аксесоари";

        }

        public override double GetCost()
        {
            return base.GetCost() + 2000.0;
        }

        public override string GetSteeringWheel()
        {
            return base.GetSteeringWheel() + " добавена настройка";
        }

        public override string GetUpholstery()
        {
            return base.GetUpholstery() + " добавена стереоуредба";
        }

        public override string GetColor()
        {
            return base.GetColor() + " металик";
        }

        public override string GetConditioning()
        {
            return "Килматроник";
        }
    }
}
