namespace Task_21_Cars.Accessories
{
    /// <summary>
    /// Abstract Decorator
    /// </summary>
    public abstract class CarAccessoriesDecorator : ICar
    {

        private ICar car;

        public CarAccessoriesDecorator(ICar car)
        {
            this.car = car;
        }

        public virtual string GetDescription()
        {
            return this.car.GetDescription();
        }

        public virtual double GetCost()
        {
            return this.car.GetCost();
        }

        public virtual string GetSteeringWheel()
        {
            return this.car.GetSteeringWheel();
        }

        public virtual string GetUpholstery()
        {
            return this.car.GetUpholstery();
        }

        public virtual string GetColor()
        {
            return this.car.GetColor();
        }

        public virtual string GetConditioning()
        {
            return this.car.GetConditioning();
        }
    }
}
