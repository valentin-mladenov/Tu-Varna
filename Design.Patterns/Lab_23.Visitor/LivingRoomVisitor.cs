using System;

namespace Lab_23.Visitor
{
    public class LivingRoomVisitor: IUnitVisitor
    {
        public void Visit(Unit livingRoom)
        {
            if (livingRoom.GetType() == typeof(LivingRoom))
            {
                Console.WriteLine("Тази стая е: " + livingRoom.ToString());
            }
        }
    }
}