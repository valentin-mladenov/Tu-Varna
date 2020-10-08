using AFE.Service.Data.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using AFE.Service.DTOs;

namespace AFE.Service.Data.Repositories.Interfaces
{
	public interface ICustomerFBRepository
	{
		CustomerFeedback Get(Guid id);
		IEnumerable<CustomerFeedback> GetPage(int pageNumber, int pageSize);
        Task<bool> PostFeedback(FeedbackModel feedback);
		IEnumerable<PieChartElement> GetChartData();


	}
}
