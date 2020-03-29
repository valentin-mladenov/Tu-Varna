namespace Lab_03.Builder
{
    public class MaleHamburgerBuilder : IBuilder
    {
        private Hamburger hamburger;

        public void AddIngredients()
        {
            this.hamburger.Ingredients = new string[] { "Хляб", "Месо", "Домат", "Салата", "Майонеза" };
        }

        public void AddShape()
        {
            this.hamburger.Shape = "Квадратен";
        }

        public void AddSize()
        {
            this.hamburger.Size = 10;
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
