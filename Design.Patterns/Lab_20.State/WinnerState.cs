using System;

namespace Lab_20.State
{
    public class WinnerState : IState
    {
        private GumballMachine Machine { get; }

        public WinnerState(GumballMachine gumballMachine)
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
            Console.WriteLine("Победи!!! 2 2 гумени топки на цената на 1");
            Machine.ReleaseBall();
            if (Machine.Count == 0)
            {
                Machine.State = Machine.SoldOutState;
                Console.WriteLine("Опааа! Няма повече гумени топки");
            }
            else
            {
                Machine.ReleaseBall();
                Machine.State = Machine.NoQuarterState;
            }
        }
    }
}