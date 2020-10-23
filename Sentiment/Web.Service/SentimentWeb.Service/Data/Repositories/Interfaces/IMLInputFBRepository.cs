using SentimentWeb.Service.DTOs;

namespace SentimentWeb.Service.Data.Repositories.Interfaces
{
	public interface IMLInputFBRepository
	{
		bool TransferToMLInput();

		bool ConfirmFeeback(FeedbackModel feedback);
	}
}
