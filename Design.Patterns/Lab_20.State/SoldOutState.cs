using System;

namespace Lab_20.State
{
    public class SoldOutState : IState
    {
        public GumballMachine Machine { get; }

        public SoldOutState(GumballMachine gumballMachine)
        {
            Machine = gumballMachine;
        }

        public void InsertQuarter()
        {
            Console.WriteLine("Съжалявам! Всички са продадени");
        }

        public void EjectQuarter()
        {
            Console.WriteLine("Не мога да изкарам монета след като няма");
        }

        public void TurnCrank()
        {
            Console.WriteLine("Завъртането на колелото не е разрешено");
        }

        public void Dispense()
        {
            Console.WriteLine("Не мога да изкарам топка след като няма");
        }
    }
}