using System.Threading.Tasks;
using SentimentML.Model;
using SentimentWeb.Service.Data.Repositories.Interfaces;
using SentimentWeb.Service.DTOs;
using Microsoft.AspNetCore.Mvc;
using LanguageML.Model;
using Microsoft.Extensions.Hosting;

namespace SentimentWeb.Service.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class MLInputFBController : ControllerBase
    {
		private readonly IMLInputFBRepository _mlInputFBRepository;
		private readonly MLModelBuilder _mlModelBuilder;
		private IHostApplicationLifetime ApplicationLifetime;


		public MLInputFBController(
			IMLInputFBRepository mlInputFBRepository,
			MLModelBuilder mlModelBuilder,
			IHostApplicationLifetime appLifetime)
		{
			ApplicationLifetime = appLifetime;
			_mlInputFBRepository = mlInputFBRepository;
			_mlModelBuilder = mlModelBuilder;
		}

		[HttpPost("all")]
		public IActionResult TransferToMLInput()
		{
			return Ok(_mlInputFBRepository.TransferToMLInput());
		}

		[HttpPut("confirm")]
		public IActionResult ConfirmFeeback([FromBody] FeedbackModel model)
		{
			var result = _mlInputFBRepository.ConfirmFeeback(model);

			return Ok(result);
		}

		[HttpPost("retrain")]
		public IActionResult RetrainTheMachine()
		{
			Task.Run(() => {
				_mlModelBuilder.InitSQL();

				LanguageMLModelBuilder.InitSQL();

				ApplicationLifetime.StopApplication();
			});

			return Ok();
		}
	}
}