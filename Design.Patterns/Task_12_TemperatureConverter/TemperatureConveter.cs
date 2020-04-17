using System;

namespace Task_12_TemperatureConverter
{
    public class TemperatureConveter
    {
        private static readonly Lazy<TemperatureConveter> lazyInstance
            = new Lazy<TemperatureConveter>(() => new TemperatureConveter());

        public static TemperatureConveter GetInstance() => lazyInstance.Value;


        private TemperatureConveter() { }

        public double ConvertCelsiusToFahrenheit(double temp)
        {
            return temp * 1.8 + 32;
        }

        public double ConvertFahrenheitToCelsius(double temp)
        {
            return (temp - 32) / 1.8;
        }
    }
}
