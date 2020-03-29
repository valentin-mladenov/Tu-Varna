namespace Lab_04.Factory
{
    using System;
    using Lab_04.Factory.Pizza;
    using Lab_05.Abstract.Factory;

    abstract class PizzaFactory
    {
        public AbstractPizza Order(string type)
        {
            var pizza = Create(type, null);

            pizza.Prepare();

            pizza.Bake();

            pizza.Cut();

            pizza.Box();

            return pizza;
        }

        protected virtual AbstractPizza Create(string type, IIngredientsFactory ingredients)
        {
            AbstractPizza pizza;

            switch (type)
            {
                case "Четири сирена":
                    pizza = new CheesePizza(ingredients);
                    break;
                case "Зверска":
                    pizza = new ClamPizza(ingredients);
                    break;
                case "Вегитарианска":
                    pizza = new VeggiePizza(ingredients);
                    break;
                default:
                    throw new ArgumentException("Няма такава пица");
            }

            return pizza;
        }
    }
}
