namespace Lab_07.Composite
{
    public class Client
    {
        private readonly IMenuComponent menus;

        public Client(IMenuComponent menus)
        {
            this.menus = menus;
        }

        public void Print()
        {
            menus.Print();
        }
    }
}