namespace Lab_14.Command
{
    internal class RemoteControl
    {
        private readonly ICommand[] offCommand;

        private readonly ICommand[] onCommand;

        private ICommand undoCommand;

        public RemoteControl(int slots)
        {
            this.onCommand = new ICommand[slots];
            this.offCommand = new ICommand[slots];

            var none = new NoCommand();
            this.undoCommand = none;
            for (var i = 0; i < slots; i++)
            {
                this.onCommand[i] = none;
                this.offCommand[i] = none;
            }
        }

        public OnOffStruct this[int i]
        {
            set
            {
                this.onCommand[i] = value.On;
                this.offCommand[i] = value.Off;
            }
        }

        public void PushOn(int slot)
        {
            this.onCommand[slot].Execute();
            this.undoCommand = this.offCommand[slot];
        }

        public void PushOff(int slot)
        {
            this.offCommand[slot].Execute();
            this.undoCommand = this.onCommand[slot];
        }

        public void PushUndo()
        {
            this.undoCommand.Execute();
        }
    }
}