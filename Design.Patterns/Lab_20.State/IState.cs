namespace Lab_20.State
{
    public interface IState
    {
        void InsertQuarter();

        void EjectQuarter();

        void TurnCrank();

        void Dispense();
    }
}