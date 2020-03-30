namespace Lab_23.Visitor
{
    public class Bedroom: Unit
    {
        public Bedroom(params Unit[] units) : base(units)
        {
        }

        public override void Accept(IUnitVisitor visitor)
        {
            visitor.Visit(this);
            base.Accept(visitor);
        }

        public override string ToString()
        {
            return "Спалня";
        }
    }
}