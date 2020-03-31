using System;
using System.Collections.Generic;
using System.Text;

namespace Lab_02.Prototype
{
    /// <summary>
    /// The 'Prototype' Interface
    /// </summary>
    public interface IColorPrototype
    {
        public IColorPrototype Clone();
    }
}
