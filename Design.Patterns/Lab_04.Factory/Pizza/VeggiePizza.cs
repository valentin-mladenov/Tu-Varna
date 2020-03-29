using System;

namespace Lab_04.Factory.Pizza
{
    using Lab_05.Abstract.Factory;
    using System.Linq;
    using System.Text;

    class VeggiePizza : AbstractPizza
    {
        readonly IIngredientsFactory ingredients;

        public VeggiePizza(IIngredientsFactory ingredients)
        {
            this.ingredients = ingredients;
        }

        internal override void Prepare()
        {
            var sb = new StringBuilder();

            sb.Append($"Тесто: {this.ingredients.CreateDough().Name},");
            sb.Append($"Кашкавал: {this.ingredients.CreateCheese().Name}, ");
            sb.Append($"Сос: {this.ingredients.CreateSauce().Name}");
            sb.AppendLine();
            sb.Append($"Зеленчуци: {string.Join(", ", this.ingredients.CreateVeggies().Select(v => v.Name))}");

            base.Prepare();
            Console.WriteLine(sb.ToString());
        }
    }
}
