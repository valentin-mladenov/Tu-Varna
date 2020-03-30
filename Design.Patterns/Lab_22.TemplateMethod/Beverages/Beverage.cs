using System;

namespace Lab_22.TemplateMethod
{
    public interface IBeverage
    {
        public bool WantsCondiments();

        public void Brew();

        public void Boil();

        public void Pour();

        public void AddCondiments();
    }
}