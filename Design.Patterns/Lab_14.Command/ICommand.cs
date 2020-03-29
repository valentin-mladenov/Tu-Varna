namespace Lab_14.Command
{
    internal interface ICommand
    {
        void Execute();

        void Undo();
    }
}