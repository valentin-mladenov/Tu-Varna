namespace Lab_04.Factory
{
    using Lab_04.Factory.Pizza;
    using Lab_05.Abstract.Factory;

    class GodzillaPizzaFactory : PizzaFactory
    {
        private const string NamePrefix = "Годзила ";
        private const string BoxColor = "Червена";

        protected override AbstractPizza Create(string type, IIngredientsFactory ingredients = null)
        {
            if (ingredients == null)
            {
                ingredients = new GodzillaIngredientsFactory();
            }

            var pizza = base.Create(type, ingredients);

            pizza.Name = NamePrefix + type;
            pizza.Color = BoxColor;

            return pizza;
        }
    }
}
