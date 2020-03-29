namespace Lab_03.Builder
{
    /// <summary>
    /// This class can also be called the Director
    /// </summary>
    public class Cook
    {
        private IBuilder builder;

        public Cook(IBuilder builder)
        {
            AcceptBuilder(builder);
        }

        public void ChangeBuilder(IBuilder builder)
        {
            AcceptBuilder(builder);
        }

        public Hamburger Build()
        {
            this.builder.AddIngredients();
            this.builder.AddShape();
            this.builder.AddSize();
            return this.builder.Build();
        }

        private void AcceptBuilder(IBuilder builder)
        {
            this.builder = builder;

            this.builder.Reset();
        }
    }
}
