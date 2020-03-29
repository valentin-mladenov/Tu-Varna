namespace Lab_07.Bridge
{
    public interface IWeapon
    {
        void Wield();
        void Swing();
        void Unwield();
        IEnchantment GetEnchantment();
    }
}