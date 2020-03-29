namespace Lab_13.ChainOfResponsibility
{
    public abstract class BaseHandler : IHandler
    {
        public void AddChain(IHandler handler)
        {
            nextInLine = handler;
        }

        public abstract double? Handle(double[] values, Action action);

        protected IHandler nextInLine;
    }
}
