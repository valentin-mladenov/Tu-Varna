using System;

namespace Lab_23.Visitor
{
    public class ApartmentVisitor: IUnitVisitor
    {
        public void Visit(Unit apartment)
        {
            if(apartment.GetType() == typeof(Apartment))
            {
                Console.WriteLine("Добре дошъл в: " + apartment.ToString());
            }
        }
    }
}