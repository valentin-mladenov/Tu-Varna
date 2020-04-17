namespace Task_21_Cars
{
    public class DeluxCar : ICar
    {
        public string GetDescription()
        {
            return "Удобна кола";
        }

        public double GetCost()
        {
            return 750000.0;
        }

        public string GetSteeringWheel()
        {
            return "Волан Удобен";
        }

        public string GetUpholstery()
        {
            return "Тапицерия Удобна";
        }

        public string GetColor()
        {
            return "Зелен";
        }

        public string GetConditioning()
        {
            return "Киматик";
        }
    }
}
