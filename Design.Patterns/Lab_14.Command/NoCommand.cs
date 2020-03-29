using System;

namespace Lab_14.Command
{
    internal class NoCommand : ICommand
    {
        public void Execute()
        {
            Console.WriteLine("Няма избрана команда");
        }

        public void Undo()
        {
            Execute();
        }
    }
}