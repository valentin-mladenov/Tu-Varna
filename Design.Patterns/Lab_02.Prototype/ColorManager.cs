using System.Collections.Generic;
using System.Linq;

namespace Lab_02.Prototype
{
    /// <summary>
    /// Prototype manager
    /// </summary>
    public class ColorManager

    {
        private Dictionary<string, IColorPrototype> colors;

        public ColorManager()
        {
            this.colors = new Dictionary<string, IColorPrototype>();
        }

        public IColorPrototype this[string key]
        {
            get { return colors[key]; }
            set { colors.Add(key, value); }
        }

        public override string ToString()
        {
            return string.Join("\r\n", colors.Select(x => $"{x.Key, -10}{x.Value}").ToArray());
        }
    }
}
