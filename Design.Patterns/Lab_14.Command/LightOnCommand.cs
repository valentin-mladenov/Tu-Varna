namespace Lab_14.Command
{
    internal class LightOnCommand : ICommand
    {
        private readonly Light light;

        public LightOnCommand(Light l)
        {
            this.light = l;
        }

        public void Execute()
        {
            this.light.On();
        }

        public void Undo()
        {
            this.light.Off();
        }
    }
}