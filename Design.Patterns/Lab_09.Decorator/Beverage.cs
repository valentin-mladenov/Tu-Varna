namespace Lab_08.Decorator
{
    abstract class Beverage
    {
        protected string _description = "Няма описание";
        public abstract string Description { get; }
        public abstract double Cost();
    }
}
