using System;

namespace Lab_07.Bridge
{
    public class FlyingEnchantment : IEnchantment
    {
        public void OnActivate()
        {
            Console.WriteLine("Елементът започва да свети слабо.");
        }

        public void Apply()
        {
            Console.WriteLine("Елементът лети и удря враговете, като накрая се връща в ръката на собственика.");
        }

        public void OnDeactivate()
        {
            Console.WriteLine("Блясъкът на продукта избледнява.");
        }
    }
}