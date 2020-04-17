namespace Task_21_Cars
{
    public class EconomyCar : ICar
    {
        public string GetDescription()
        {
            return "Икономична кола";
        }

        public double GetCost()
        {
            return 450000.0;
        }

        public string GetSteeringWheel()
        {
            return "Волан Обикновен";
        }

        public string GetUpholstery()
        {
            return "Тапицерия Обикновена";
        }

        public string GetColor()
        {
            return "Бял";
        }

        public string GetConditioning()
        {
            return "Няма";
        }
    }
}
