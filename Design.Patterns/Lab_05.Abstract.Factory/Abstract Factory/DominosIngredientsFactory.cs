using System.Collections.Generic;

namespace Lab_05.Abstract.Factory
{
    using Lab_05.Abstract.Factory.Ingrediants;
    using Lab_05.Abstract.Factory.Ingrediants.Interfaces;
    
    public class DominosIngredientsFactory : IIngredientsFactory
    {
        ICheese IIngredientsFactory.CreateCheese()
        {
            return new Mozarella();
        }

        IClam IIngredientsFactory.CreateClam()
        {
            return new FrozenClam();
        }

        IDough IIngredientsFactory.CreateDough()
        {
            return new ThinCrust();
        }

        ISauce IIngredientsFactory.CreateSauce()
        {
            return new CherryTomato();
        }

        IEnumerable<IVeggie> IIngredientsFactory.CreateVeggies()
        {
            return new IVeggie[]{ new Onion(), new Pepper(), new Olive() };
        }
    }
}