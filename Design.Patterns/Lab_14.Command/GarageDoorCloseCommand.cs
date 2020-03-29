namespace Lab_14.Command
{
    internal class GarageDoorCloseCommand : ICommand
    {
        private readonly Garage garage;

        public GarageDoorCloseCommand(Garage g)
        {
            this.garage = g;
        }

        public void Execute()
        {
            this.garage.Close();
        }

        public void Undo()
        {
            this.garage.Open();
        }
    }
}