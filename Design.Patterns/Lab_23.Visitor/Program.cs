using System;

namespace Lab_23.Visitor
{
    class Program
    {
        static void Main(string[] args)
        {
            var apartment = new Apartment(new LivingRoom(), new Bedroom(), new Bedroom());
            var studio = new Studio(new LivingRoom(), new Bedroom());

            Console.WriteLine("Посещаваме Апартамент");
            apartment.Accept(new ApartmentVisitor());
            apartment.Accept(new LivingRoomVisitor());
            apartment.Accept(new BedroomVisitor());
            Console.WriteLine();

            Console.WriteLine("Посещаваме Студио");
            studio.Accept(new StudioVisitor());
            studio.Accept(new LivingRoomVisitor());
            studio.Accept(new BedroomVisitor());
        }
    }
}
