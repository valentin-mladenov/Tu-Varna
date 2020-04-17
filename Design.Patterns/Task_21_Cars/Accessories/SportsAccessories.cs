namespace Task_21_Cars.Accessories
{
    /// <summary>
    /// Sports Accessories Decorator
    /// </summary>
    public class SportsAccessories : CarAccessoriesDecorator
    {
        public SportsAccessories(ICar car)
        : base(car)
        {

        }

        public override string GetDescription()
        {
            return base.GetDescription() + ", Пакет спортни аксесоари";
        }

        public override double GetCost()
        {
            return base.GetCost() + 15000.0;
        }

        public override string GetSteeringWheel()
        {
            return base.GetSteeringWheel() + " ама спортен";
        }

        public override string GetUpholstery()
        {
            return base.GetUpholstery() + " ама със спортни нюанси";
        }

        public override string GetColor()
        {
            return base.GetColor() + " добавени съзтезаателни черти";
        }

        public override string GetConditioning()
        {
            return "Климатик";
        }
    }
}
