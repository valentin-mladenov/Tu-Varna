using System.Collections.Generic;

namespace Lab_05.Abstract.Factory
{
    using Lab_05.Abstract.Factory.Ingrediants;
    using Lab_05.Abstract.Factory.Ingrediants.Interfaces;

    public class GodzillaIngredientsFactory : IIngredientsFactory
    {
        ICheese IIngredientsFactory.CreateCheese()
        {
            return new Parmesan();
        }

        IClam IIngredientsFactory.CreateClam()
        {
            return new FreshClam();
        }

        IDough IIngredientsFactory.CreateDough()
        {
            return new DeepDish();
        }

        ISauce IIngredientsFactory.CreateSauce()
        {
            return new PlumTomato();
        }

        IEnumerable<IVeggie> IIngredientsFactory.CreateVeggies()
        {
            return new IVeggie[] { new Onion(), new Cucumber(), new Pepper() };
        }
    }
}