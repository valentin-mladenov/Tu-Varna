using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using SentimentWeb.Service.Data.Entities;

namespace SentimentWeb.Service.Data.Repositories.Interfaces
{
	public interface IMLInputFBRepository
	{
		bool TransferToMLInput();

		bool ConfirmFeeback(long customerFeedbackId, bool sentiment);
	}
}
