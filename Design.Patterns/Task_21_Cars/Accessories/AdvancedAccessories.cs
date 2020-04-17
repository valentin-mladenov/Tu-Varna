namespace Task_21_Cars.Accessories
{
    /// <summary>
    /// Advanced Accessories Decorator
    /// </summary>
    public class AdvancedAccessories : CarAccessoriesDecorator
    {
        public AdvancedAccessories(ICar car)
        : base(car)
        {

        }

        public override string GetDescription()
        {
            return base.GetDescription() + ", Пакет подобрени аксесоари";
        }

        public override double GetCost()
        {
            return base.GetCost() + 10000.0;
        }
        public override string GetSteeringWheel()
        {
            return base.GetSteeringWheel() + " основен пакет плюс бутони";
        }

        public override string GetUpholstery()
        {
            return base.GetUpholstery() + " Кожена";
        }

        public override string GetColor()
        {
            return base.GetColor() + " перла";
        }

        public override string GetConditioning()
        {
            return "4 зонов Климатроник";
        }
    }
}
