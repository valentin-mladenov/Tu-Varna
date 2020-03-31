namespace Lab_15.Interpreter
{
    public class Context
    {
        public Context(string input)
        {
            this.Input = input;
        }

        // Gets or sets input
        public string Input { get; set; }

        // Gets or sets output
        public int Output { get; set; }
    }
}
