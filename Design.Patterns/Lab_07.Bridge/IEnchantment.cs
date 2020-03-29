namespace Lab_07.Bridge
{
    public interface IEnchantment
    {
        void OnActivate();
        void Apply();
        void OnDeactivate();
    }
}