namespace Lab_16.Iterator
{
    using System.Collections.Generic;
    using Lab_08.Composite;

    public class Client
    {
        private IEnumerable<IMenuComponent> dishes;

        public Client(IEnumerable<IMenuComponent> dishes)
        {
            this.dishes = dishes;
        }

        public void PrintMenu()
        {
            foreach (var dish in dishes)
            {
                dish.Print();
            }
        }
    }
}