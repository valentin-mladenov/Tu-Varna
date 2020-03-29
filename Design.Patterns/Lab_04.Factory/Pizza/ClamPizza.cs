using System;

namespace Lab_04.Factory.Pizza
{
    using Lab_05.Abstract.Factory;
    using System.Linq;
    using System.Text;

    class ClamPizza : AbstractPizza
    {
        readonly IIngredientsFactory ingredients;

        public ClamPizza(IIngredientsFactory ingredients)
        {
            this.ingredients = ingredients;
        }

        internal override void Prepare()
        {
            var sb = new StringBuilder();

            sb.Append($"Тесто: {this.ingredients.CreateDough().Name}, ");
            sb.Append($"Миди: {this.ingredients.CreateClam().Name}, ");
            sb.Append($"Сос: {this.ingredients.CreateSauce().Name}, ");
            sb.Append($"Кашкавал: {this.ingredients.CreateCheese().Name}");

            base.Prepare();
            Console.WriteLine(sb.ToString());
        }
    }
}
