namespace Lab_14.Command
{
    internal class GarageDoorOpenCommand : ICommand
    {
        private readonly Garage garage;

        public GarageDoorOpenCommand(Garage g)
        {
            this.garage = g;
        }

        public void Execute()
        {
            this.garage.Open();
        }

        public void Undo()
        {
            this.garage.Close();
        }
    }
}