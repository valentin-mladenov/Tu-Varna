namespace Lab_13.ChainOfResponsibility
{
    public class AdditionHandler : BaseHandler
    {
        public override double? Handle(double[] values, Action action)
        {
            if (action == Action.Addition)
            {
                double result = 0.0;
                foreach (var value in values)
                {
                    result += value;
                }
                return result;
            }

            return base.nextInLine?.Handle(values, action);
        }
    }
}
