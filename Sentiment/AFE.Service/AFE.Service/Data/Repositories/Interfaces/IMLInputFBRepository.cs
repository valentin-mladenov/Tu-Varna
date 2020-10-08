using AFE.Service.Data.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace AFE.Service.Data.Repositories.Interfaces
{
	public interface IMLInputFBRepository
	{
		bool TransferToMLInput();

		bool ConfirmFeeback(Guid customerFeedbackId, bool sentiment);
	}
}
