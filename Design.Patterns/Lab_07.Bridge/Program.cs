using System;

namespace Lab_07.Bridge
{
    class Program
    {
        static void Main(string[] args)
        {
            IWeapon sword = new Sword(new FlyingEnchantment());
            sword.Wield();
            sword.Swing();
            sword.Unwield();

            IWeapon hammer = new Hammer(new SoulEatingEnchantment());
            hammer.Wield();
            hammer.Swing();
            hammer.Unwield();
        }
    }
}
