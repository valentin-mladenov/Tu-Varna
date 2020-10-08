using System;
using System.Threading.Tasks;
using AFE.Service.Data.Repositories.Interfaces;
using AFE.Service.DTOs;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json.Linq;


namespace AFE.Service.Controllers
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
            var result =await _customerFBRepository.PostFeedback(feedback);
            return Ok(result);
        }

        [HttpGet]
        public IActionResult GetPage([FromQuery] int pageNumber, [FromQuery] int pageSize)
        {
            if (pageSize <= 0)
            {
                pageSize = 50;
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