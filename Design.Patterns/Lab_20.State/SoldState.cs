using System;

namespace Lab_20.State
{
    public class SoldState : IState
    {
        private GumballMachine Machine { get; }

        public SoldState(GumballMachine gumballMachine)
        {
            Machine = gumballMachine;
        }

        public void InsertQuarter()
        {
            Console.WriteLine("Моля изчакайте, вече се изпълнява");
        }

        public void EjectQuarter()
        {
           Console.WriteLine("Не може да изкарате монетата, колелото се върти");
        }

        public void TurnCrank()
        {
            Console.WriteLine("Само едно завъртане е разрешено");
        }

        public void Dispense()
        {
            Machine.ReleaseBall();
            if (Machine.Count > 0)
            {
                Machine.State = Machine.NoQuarterState;
            }
            else
            {
                Console.WriteLine("Опааа! Няма повече гумени топки");
                Machine.State = Machine.SoldOutState;
            }
        }
    }
}