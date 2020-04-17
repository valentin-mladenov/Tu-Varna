// Façade
// Да се създаде приложение Pizza.

using System;

namespace Task_23.Pizza
{
    class Program
    {
        static void Main(string[] args)
        {
            var pizzaFacade = new PizzaRestaurantFacade();

            Console.WriteLine(pizzaFacade.GetPepperoniPizza());
            Console.WriteLine(pizzaFacade.GetCheesePizza());
            Console.WriteLine(pizzaFacade.GetVeggiePizza());
        }
    }
}
