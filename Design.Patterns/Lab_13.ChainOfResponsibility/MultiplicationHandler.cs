namespace Lab_13.ChainOfResponsibility
{
    public class MultiplicationHandler : BaseHandler
    {
        public override double? Handle(double[] values, Action action)
        {
            if (action == Action.Multiplication)
            {
                var result = 1.0;
                foreach (var value in values)
                {
                    result *= value;
                }
                return result;
            }

            return base.nextInLine?.Handle(values, action);
        }
    }
}
