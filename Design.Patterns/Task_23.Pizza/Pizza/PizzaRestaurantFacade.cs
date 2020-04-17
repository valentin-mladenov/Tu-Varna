namespace Task_23.Pizza
{
    /// <summary>
    /// Pizza Restaurant Facade
    /// </summary>
    public class PizzaRestaurantFacade
    {
        private IPizza veggiePizzaProvider;
        private IPizza pepperoniPizzaProvider;
        private IPizza cheesePizzaProvider;

        public PizzaRestaurantFacade()
        {
            veggiePizzaProvider = new VeggiePizzaProvider();
            pepperoniPizzaProvider = new PepperoniPizzaProvider();
            cheesePizzaProvider = new CheesePizzaProvider();
        }

        public string GetPepperoniPizza()
        {
            return pepperoniPizzaProvider.GetPizza();
        }

        public string GetCheesePizza()
        {
            return cheesePizzaProvider.GetPizza();
        }

        public string GetVeggiePizza()
        {
            return veggiePizzaProvider.GetPizza();
        }
    }
}
