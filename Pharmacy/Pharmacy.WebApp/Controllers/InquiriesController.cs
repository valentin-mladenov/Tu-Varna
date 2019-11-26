using CsvHelper;
using Pharmacy.WebApp.Models.DataAccessLayer;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Web;
using System.Web.Mvc;

namespace Pharmacy.WebApp.Controllers
{
    public class InquiriesController : Controller
    {
        private PharmacyDbContext db = new PharmacyDbContext();

        // GET: LowestQuantityMedicines
        public ActionResult LowestQuantityMedicines()
        {
            var medicines = db.Medicines
                .ToList()
                .Where(f => f.AvailableQuantity <= 100)
                .OrderBy(f => f.AvailableQuantity)
                .ToList();

            ViewBag.CsvString = this.GenerateCsvString(medicines.ToList<object>());
            return View(medicines);
        }

        // GET: TopTenMostSoldMedicines
        public ActionResult TopTenMostSoldMedicines()
        {
            var medicines = db.Medicines
                .OrderByDescending(f => f.SoldMedicines.Select(sm => sm.Quantity).Sum())
                .Take(10)
                .ToList();

            ViewBag.CsvString = this.GenerateCsvString(medicines.ToList<object>());
            return View(medicines);
        }

        // GET: TopTenMostProfitableMedicines
        public ActionResult TopTenMostProfitableMedicines()
        {
            var medicines = db.Medicines
                .OrderByDescending(m => 
                    m.SoldMedicines.Select(sm => sm.Quantity * sm.Price).Sum() - 
                    m.DeliveredMedicines.Select(sm => sm.Quantity * sm.Price).Sum()
                )
                .Take(10)
                .ToList();

            ViewBag.CsvString = this.GenerateCsvString(medicines.ToList<object>());
            return View(medicines);
        }

        // GET: TopTenMostProfitableClients
        public ActionResult TopTenMostProfitableClients()
        {
            var persons = db.Persons
                .OrderByDescending(p => p.Sales.Select(s => s.SoldMedicines.Select(sm => sm.Quantity * sm.Price).Sum()).Sum())
                .Take(10)
                .ToList();

            var csvString = this.GenerateCsvString(persons.ToList<object>());
            return View(persons);
        }

        // GET: MedicineLowestPricesWithSupplier
        public ActionResult MedicineLowestPricesWithSupplier()
        {
            var medicines = db.Medicines
                .OrderBy(m => m.DeliveredMedicines.Min(sm => sm.Price))
                .ToList();

            var csvString = this.GenerateCsvString(medicines.ToList<object>());
            return View(medicines);
        }

        private string GenerateCsvString(List<object> viewModels)
        {
            if (viewModels.Count == 0)
            {
                return string.Empty;
            }

            using (var memoryStream = new MemoryStream())
            {
                using (var streamWriter = new StreamWriter(memoryStream))
                using (var csvWriter = new CsvWriter(streamWriter))
                {
                    csvWriter.WriteRecords(viewModels);
                } // StreamWriter gets flushed here.

                return "data:text/csv;charset=utf-8, " + Encoding.Default.GetString(memoryStream.ToArray());
            }
        }
    }
}