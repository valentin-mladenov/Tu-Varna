namespace Lab_13.ChainOfResponsibility
{
    public interface IHandler
    {
        void AddChain(IHandler handler);

        double? Handle(double[] values, Action action);
    }
}
