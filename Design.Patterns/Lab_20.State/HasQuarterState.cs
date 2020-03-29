using System;

namespace Lab_20.State
{
    public class HasQuarterState : IState
    {
        private GumballMachine Machine { get; }
        readonly Random _random = new Random(DateTime.Now.Millisecond);

        public HasQuarterState(GumballMachine gumballMachine)
        {
            Machine = gumballMachine;
        }

        public void InsertQuarter()
        {
            Console.WriteLine("Не може да се вкарате повече от една монета");
        }

        public void EjectQuarter()
        {
            Console.WriteLine("Връщане на монета");
            Machine.State = Machine.NoQuarterState;
        }

        public void TurnCrank()
        {
            Console.WriteLine("Завъртяхте ръчката");
            var winner = _random.Next(10);
            if ((winner == 5) && (Machine.Count > 1))
                Machine.State = Machine.WinnerState;
            else
            {
                Machine.State = Machine.SoldState;
            }
        }

        public void Dispense()
        {
            Console.WriteLine("Не може да правиш така");
        }
    }
}