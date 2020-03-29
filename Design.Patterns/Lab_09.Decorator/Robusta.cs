namespace Lab_08.Decorator
{
    class Robusta : Beverage
    {
        public Robusta()
        {
            _description = "кафе Робуста";
        }

        public override string Description => _description;

        public override double Cost()
        {
            return 2.49;
        }
    }
}
