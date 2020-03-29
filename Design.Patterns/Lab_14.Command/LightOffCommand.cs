namespace Lab_14.Command
{
    internal class LightOffCommand : ICommand
    {
        private readonly Light light;

        public LightOffCommand(Light l)
        {
            this.light = l;
        }

        public void Execute()
        {
            this.light.Off();
        }

        public void Undo()
        {
            this.light.On();
        }
    }
}