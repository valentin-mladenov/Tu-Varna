using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SentimentWeb.Service.DTOs
{
	public class PieChart
	{
		public string Name { get; set; }

		public IList<PieChartElement> Value { get; set; }
	}
}
