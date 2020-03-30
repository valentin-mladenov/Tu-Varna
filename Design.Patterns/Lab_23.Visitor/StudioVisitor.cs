using System;

namespace Lab_23.Visitor
{
    public class StudioVisitor: IUnitVisitor
    {
        public void Visit(Unit studio)
        {
            if (studio.GetType() == typeof(Studio))
            {
                Console.WriteLine("Това е: " + studio.ToString());
            }
        }
    }
}