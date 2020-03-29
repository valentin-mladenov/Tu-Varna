namespace Lab_16.Iterator
{
    using Lab_08.Composite;
    using System.Collections.Generic;

    class Program
    {
        static void Main(string[] args)
        {
            // Lab_08.Composite е вмъкнат итератор
            var menu = CreateMenu.Get();

            menu.Print();

            var breakfast = menu.GetChild("Закуска");
            var dinner = menu.GetChild("Вечеря");
            var pankakes = (breakfast as Menu).GetChild("Палачинки");
            var burgerT = (dinner as Menu).GetChild("Телешки бургер");

            var order = new List<IMenuComponent>
            {
                pankakes,
                burgerT
            };

            var waiter = new Client(order);

            waiter.PrintMenu();
        }
    }
}
