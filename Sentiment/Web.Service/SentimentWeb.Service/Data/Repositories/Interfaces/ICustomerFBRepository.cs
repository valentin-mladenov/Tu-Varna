using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using SentimentWeb.Service.Data.Entities;
using SentimentWeb.Service.DTOs;

namespace SentimentWeb.Service.Data.Repositories.Interfaces
{
	public interface ICustomerFBRepository
	{
		CustomerFeedback Get(Guid id);
		IEnumerable<CustomerFeedback> GetPage(int pageNumber, int pageSize);
        Task<bool> PostFeedback(FeedbackModel feedback);
		IEnumerable<PieChartElement> GetChartData();


	}
}
