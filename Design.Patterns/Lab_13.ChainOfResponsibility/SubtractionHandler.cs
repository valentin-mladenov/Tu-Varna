namespace Lab_13.ChainOfResponsibility
{
    public class SubtractionHandler : BaseHandler
    {
        public override double? Handle(double[] values, Action action)
        {
            if (action == Action.Subtraction)
            {
                var result = values[0];
                for (int i = 1; i < values.Length; i++)
                {
                    result -= values[i];
                }
                return result;
            }

            return base.nextInLine?.Handle(values, action);
        }
    }
}
