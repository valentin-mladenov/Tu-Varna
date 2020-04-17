namespace Task_21_Cars
{
    public class LuxuryCar : ICar
    {
        public string GetDescription()
        {
            return "Луксозна кола";
        }

        public double GetCost()
        {
            return 1000000.0;
        }

        public string GetSteeringWheel()
        {
            return "Волан Луксозен";
        }

        public string GetUpholstery()
        {
            return "Тапицерия Луксозна";
        }

        public string GetColor()
        {
            return "Черен";
        }

        public string GetConditioning()
        {
            return "Киматик";
        }
    }
}
