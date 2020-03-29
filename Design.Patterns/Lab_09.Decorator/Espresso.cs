namespace Lab_08.Decorator
{
    class Espresso : Beverage
    {
        public Espresso()
        {
            _description = "Еспресо";
        }

        public override string Description => _description;

        public override double Cost()
        {
            return 1.99;
        }
    }
}
