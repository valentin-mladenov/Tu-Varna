namespace Lab_08.Decorator
{
    internal class Arabica : Beverage
    {
        public Arabica()
        {
            _description = "кафе Арабика";
        }

        public override string Description => _description;

        public override double Cost()
        {
            return 1.49;
        }
    }
}