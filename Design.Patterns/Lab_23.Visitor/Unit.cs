namespace Lab_23.Visitor
{
    public abstract class Unit
    {
        private Unit[] units;

        public Unit(params Unit[] units)
        {
            this.units = units;
        }

        public virtual void Accept(IUnitVisitor visitor)
        {
            foreach (var unit in this.units)
            {
                unit.Accept(visitor);
            }
        }

        public abstract string ToString();
    }
}