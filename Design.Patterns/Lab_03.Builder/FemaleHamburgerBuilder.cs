using System;

namespace Lab_03.Builder
{
    public class FemaleHamburgerBuilder : IBuilder
    {
        private Hamburger hamburger;

        public void AddIngredients()
        {
            this.hamburger.Ingredients = new string[] { "Хляб", "Салата" };
        }

        public void AddShape()
        {
            this.hamburger.Shape = "Триъгълен";
        }

        public void AddSize()
        {
            this.hamburger.Size = 7;
        }

        public void Reset()
        {
            this.hamburger = new Hamburger();
        }

        public Hamburger Build()
        {
            return this.hamburger;
        }
    }
}
