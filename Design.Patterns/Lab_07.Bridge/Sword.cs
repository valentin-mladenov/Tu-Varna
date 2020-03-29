using System;

namespace Lab_07.Bridge
{
    public class Sword : IWeapon
    {
        private readonly IEnchantment _enchantment;

        public Sword(IEnchantment enchantment)
        {
            _enchantment = enchantment;
        }

        public void Wield()
        {
            Console.WriteLine("Мечът е изваден.");
            _enchantment.OnActivate();
        }

        public void Swing()
        {
            Console.WriteLine("Замахване с меча.");
            _enchantment.Apply();
        }

        public void Unwield()
        {
            Console.WriteLine("Мечът е прибран.");
            _enchantment.OnDeactivate();
        }

        public IEnchantment GetEnchantment()
        {
            return _enchantment;
        }
    }
}