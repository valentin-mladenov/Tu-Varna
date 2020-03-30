namespace Lab_21.Strategy
{
    internal class Duck
    {
        private IQuackBehaviour _quacker;
        private IFlyBehaviour _flyer;


        public IQuackBehaviour Quacker
        {
            set
            {
                _quacker = value;
            }
        }

        public IFlyBehaviour Flyer
        {
            set
            {
                _flyer = value;
            }
        }


        protected void PerformQuack()
        {
            _quacker.Quack();
        }

        protected void PerformFly()
        {
            _flyer.Fly();
        }
    }
}
