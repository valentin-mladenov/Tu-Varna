using System;
using System.Collections.Generic;
using System.Text;

namespace Lab_17.Mediator
{
    /// <summary>
    /// A 'ConcreteColleague' class
    /// </summary>
    class Male : Participant
    {
        /// <summary>
        /// Constructor
        /// </summary>
        /// <param name="name"></param>
        public Male(string name)
          : base(name)
        {
        }

        public override void Receive(string from, string message)
        {
            base.Receive(from, message);
        }
    }
}
