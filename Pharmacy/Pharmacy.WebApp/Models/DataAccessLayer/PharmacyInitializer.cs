using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace Pharmacy.WebApp.Models.DataAccessLayer
{
    public class PharmacyInitializer : CreateDatabaseIfNotExists<PharmacyDbContext>
    {
        protected override void Seed(PharmacyDbContext context)
        {

            //var driverLicenses = this.GenerateDriversLicenses();

            //context.DriverLicenses.AddRange(driverLicenses);

            //var clients = this.GenerateClients(driverLicenses);

            //context.Clients.AddRange(clients);

            //var vbrands = this.GenerateBrands();

            //context.VehicleBrands.AddRange(vbrands);

            //var vmodels = this.GenerateModels(vbrands);

            //context.VehicleModels.AddRange(vmodels);

            //var vehacles = this.GenerateVehicles(vmodels);

            //context.Vehicles.AddRange(vehacles);

            //var rentals = this.GenerateRents(vehacles, clients);

            //context.Rentals.AddRange(rentals);

            //context.SaveChanges();
        }
    }
}