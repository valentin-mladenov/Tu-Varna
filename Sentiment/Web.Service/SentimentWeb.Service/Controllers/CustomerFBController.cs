using System.Threading.Tasks;
using SentimentWeb.Service.Data.Repositories.Interfaces;
using SentimentWeb.Service.DTOs;
using Microsoft.AspNetCore.Mvc;


namespace SentimentWeb.Service.Controllers
{
    [Route("api/feedback")]
    [ApiController]
    public class CustomerFBController : ControllerBase
    {
        private readonly ICustomerFBRepository _customerFBRepository;

        public CustomerFBController(ICustomerFBRepository customerFBRepository)
        {
            _customerFBRepository = customerFBRepository;
        }

        [HttpPost]
        public async Task<IActionResult> Post([FromBody]FeedbackModel feedback)
        {
            var result = await _customerFBRepository.PostFeedback(feedback);

            return Ok(result);
        }

        [HttpGet]
        public IActionResult GetPage([FromQuery] int pageNumber, [FromQuery] int pageSize)
        {
            if (pageSize <= 0)
            {
                pageSize = 1000;
            }

            if (pageNumber < 0)
            {
                pageNumber = 0;
            }

            return Ok(_customerFBRepository.GetPage(pageNumber, pageSize));
        }

		[HttpGet("chart")]
		public IActionResult GetChartData()
		{
			return Ok(_customerFBRepository.GetChartData());
		}
	}
}