using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab_09.Facade
{
    public class Dimmer
    {
        internal void Dim(int val)
        {
            var dimmed = val == 10
                ? "Включване на светлините на пълна мощност"
                : $"Светлината настроена на ниво {val}";

            Console.WriteLine(dimmed);
        }

        internal void Off() => Console.WriteLine("Светлините са изключени");
    }
}
