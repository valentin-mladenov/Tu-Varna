namespace Lab_14.Command
{
    internal class MacroCommand : ICommand
    {
        private readonly ICommand[] commands;

        public MacroCommand(ICommand[] commands)
        {
            this.commands = commands;
        }

        public void Execute()
        {
            foreach (var item in this.commands)
            {
                item.Execute();
            }
        }

        public void Undo()
        {
            foreach (var item in this.commands)
            {
                item.Undo();
            }
        }
    }
}