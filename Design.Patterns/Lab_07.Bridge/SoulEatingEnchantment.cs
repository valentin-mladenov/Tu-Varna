using System;

namespace Lab_07.Bridge
{
    public class SoulEatingEnchantment:IEnchantment
    {
        public void OnActivate()
        {
            Console.WriteLine("Елементът разпространява кръвожадност.");
        }

        public void Apply()
        {
            Console.WriteLine("Елементът изяжда душите на врагове.");
        }

        public void OnDeactivate()
        {
            Console.WriteLine("Кръвожадността бавно изчезва.");
        }
    }
}