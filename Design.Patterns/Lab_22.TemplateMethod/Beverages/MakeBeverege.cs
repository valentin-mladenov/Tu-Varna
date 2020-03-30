namespace Lab_22.TemplateMethod.Beverages
{
    public class MakeBeverege
    {
        public void Prepare(IBeverage beverage)
        {
            beverage.Boil();
            beverage.Brew();
            beverage.Pour();
            if (beverage.WantsCondiments())
            {
                beverage.AddCondiments();
            }
        }
    }
}
