using System.Collections.Generic;

namespace Lab_05.Abstract.Factory
{
    using Lab_05.Abstract.Factory.Ingrediants.Interfaces;

    public interface IIngredientsFactory
    {
        IDough CreateDough();
        IEnumerable<IVeggie> CreateVeggies();
        ISauce CreateSauce();
        ICheese CreateCheese();
        IClam CreateClam();
    }
}
