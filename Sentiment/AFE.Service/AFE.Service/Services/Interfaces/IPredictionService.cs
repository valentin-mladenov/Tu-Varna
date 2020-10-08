using AFE.Model.Fabric.Models;
using AFE.Service.Data.Entities;

namespace AFE.Service.Data.Services.Interfaces
{
    public interface IPredictionRepository
    {
        CustomerFeedback Predict(string feedback);
    }
}
