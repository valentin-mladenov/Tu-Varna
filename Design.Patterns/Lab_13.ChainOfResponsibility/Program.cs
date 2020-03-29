using System;

namespace Lab_13.ChainOfResponsibility
{
    class Program
    {
        static void Main(string[] args)
        {
            var firstHandler = new AdditionHandler();
            var secondHandler = new SubtractionHandler();
            var thirdHander = new MultiplicationHandler();

            //Създаваме верига
            firstHandler.AddChain(secondHandler);
            secondHandler.AddChain(thirdHander);

            //Execution
            double[] numbers = new double[] { 2, 3, 4, 5 };
            var additionResult = firstHandler.Handle(numbers, Action.Addition);
            var subtractionResult = firstHandler.Handle(numbers, Action.Subtraction);
            var multResult = firstHandler.Handle(numbers, Action.Multiplication);
            var divisionResult = firstHandler.Handle(numbers, Action.Divison); // Делението не е част от веригата!!!

            Console.WriteLine("Събиране = {0}", additionResult);
            Console.WriteLine("Изваждане = {0}", subtractionResult);
            Console.WriteLine("Умножение = {0}", multResult);
            Console.WriteLine("Деление = {0}", divisionResult.HasValue ? divisionResult.Value.ToString() : "NULL");
        }
    }
}
