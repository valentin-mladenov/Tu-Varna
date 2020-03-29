using System;

namespace Lab_20.State
{
    public class NoQuarterState : IState
    {
        public GumballMachine Machine { get; }

        public NoQuarterState(GumballMachine machine)
        {
            Machine = machine;
        }
        public void InsertQuarter()
        {
            Console.WriteLine("Вкарайте монета");
            Machine.State = Machine.HasQuarterState;
        }

        public void EjectQuarter()
        {
            Console.Write("Няма вкарана монета");
        }

        public void TurnCrank()
        {
           Console.WriteLine("Без монета няма врътка");
        }

        public void Dispense()
        {
            Console.WriteLine("Не може да се изпълни");
        }
    }
}