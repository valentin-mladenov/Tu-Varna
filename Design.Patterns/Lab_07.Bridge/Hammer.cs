using System;

namespace Lab_07.Bridge
{
    public class Hammer : IWeapon
    {
        private readonly IEnchantment _enchantment;
        public Hammer(IEnchantment enchantment)
        {
            _enchantment = enchantment;
        }

        public void Wield()
        {
            Console.WriteLine("Чукът е изваден.");
            _enchantment.OnActivate();
        }

        public void Swing()
        {
            Console.WriteLine("Замах с чукът.");
            _enchantment.Apply();
        }

        public void Unwield()
        {
            Console.WriteLine("Чукът е прибран.");
            _enchantment.OnDeactivate();
        }

        public IEnchantment GetEnchantment()
        {
            return _enchantment;
        }
    }
}