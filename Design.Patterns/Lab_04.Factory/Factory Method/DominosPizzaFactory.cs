namespace Lab_04.Factory
{
    using Lab_04.Factory.Pizza;
    using Lab_05.Abstract.Factory;

    class DominosPizzaFactory : PizzaFactory
    {
        private const string NamePrefix = "Доминос ";
        private const string BoxColor = "Синя";

        protected override AbstractPizza Create(string type, IIngredientsFactory ingredients = null)
        {
            if (ingredients == null)
            {
                ingredients = new DominosIngredientsFactory();
            }
            
            var pizza = base.Create(type, ingredients);

            pizza.Name = NamePrefix + type;
            pizza.Color = BoxColor;

            return pizza;
        }
    }
}
