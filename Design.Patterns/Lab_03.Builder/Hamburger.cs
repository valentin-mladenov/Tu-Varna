namespace Lab_03.Builder
{
    public class Hamburger
    {
        public int Size { get; set; }
        public string Shape { get; set; }
        public string[] Ingredients { get; set; }
        public override string ToString()
        {
            return $"Съставки: {string.Join(", ", this.Ingredients)}, Размер: {Size} см^2, Форма: {Shape}";
        }
    }
}
