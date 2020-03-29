using System;

namespace Lab_20.State.Legacy
{
    public class GumballMachine
    {
        private int _count;
        private State _state = State.NoQuarters;

        public GumballMachine(int count)
        {
            _count = count;
        }

        public void InsertQuarter()
        {
            switch (_state)
            {
                case State.NoQuarters:
                    _state = State.HasQuarters;
                    Console.WriteLine("Вкарай монета");
                    break;
                case State.Sold:
                    Console.WriteLine("Изчакайте гумената топка да излезе");
                    break;
                case State.HasQuarters:
                    Console.WriteLine("Не може да добавите повече монети");
                    break;
                case State.NoGumballs:
                    Console.WriteLine("Няма повече гумени топки");
                    break;
                default:
                    throw new ArgumentOutOfRangeException();
            }
        }

        public void EjectQuarter()
        {
            switch (_state)
            {
                case State.NoQuarters:
                    Console.WriteLine("Не мога върна. Няма монета");
                    break;
                case State.Sold:
                    Console.WriteLine("Съжалявам, вече завъртяхте ръчката");
                    break;
                case State.HasQuarters:
                    Console.WriteLine("Връщане на монета");
                    _state = State.NoQuarters;
                    break;
                case State.NoGumballs:
                    Console.WriteLine("Не мога върна. Няма пусната монета");
                    break;
                default:
                    throw new ArgumentOutOfRangeException();
            }
        }

        public void TurnCrank()
        {
            switch (_state)
            {
                case State.NoQuarters:
                    Console.WriteLine("Първо Вкарайте монета");
                    break;
                case State.Sold:
                    Console.WriteLine("Само едно завъртане е разрешено");
                    break;
                case State.HasQuarters:
                    Console.WriteLine("Вземане на топка...");
                    _state = State.Sold;
                    Dispense();
                    break;
                case State.NoGumballs:
                    Console.WriteLine("Топките са свършили");
                    break;
                default:
                    throw new ArgumentOutOfRangeException();
            }
        }

        private void Dispense()
        {
            switch (_state)
            {
                case State.NoQuarters:
                    Console.WriteLine("Първо поснете монета");
                    break;
                case State.Sold:
                    Console.WriteLine("Гумена топка излиза...");
                    _count--;
                    _state = _count == 0 ? _state = State.NoGumballs : State.NoQuarters;
                    break;
                case State.HasQuarters:
                case State.NoGumballs:
                    Console.WriteLine("Топките са свършили");
                    break;
                default:
                    throw new ArgumentOutOfRangeException();
            }
        }
    }
}