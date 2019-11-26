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
            var medicines = this.GenerateMedicines();

            context.Medicines.AddRange(medicines);

            var people = this.GeneratePeople();

            context.Persons.AddRange(people);

            var firms = this.GenerateFirms();

            context.Counterparties.AddRange(firms);

            var deliveries = this.GenerateDeliveries(firms);

            context.Deliveries.AddRange(deliveries);

            var sells = this.GenerateSells(people);

            context.Sells.AddRange(sells);

            var deliveredMedicines = this.GenerateDeliveredMedicines(deliveries, medicines);

            context.DeliveredMedicines.AddRange(deliveredMedicines);

            var soldMedicines = this.GenerateSoldMedicines(sells, medicines);

            context.SoldMedicines.AddRange(soldMedicines);

            context.SaveChanges();
        }

        private List<Medicine> GenerateMedicines()
        {
            return new List<Medicine>
            {
                new Medicine
                {
                    Id = Guid.NewGuid(),
                    Name = "Omeprazol",
                    Code = "oprz159",
                },
                new Medicine
                {
                    Id = Guid.NewGuid(),
                    Name = "Insulin",
                    Code = "insln536",
                },
                new Medicine
                {
                    Id = Guid.NewGuid(),
                    Name = "Analgin",
                    Code = "anlgn56",
                },
                new Medicine
                {
                    Id = Guid.NewGuid(),
                    Name = "Aspirin",
                    Code = "asprn22",
                },
                new Medicine
                {
                    Id = Guid.NewGuid(),
                    Name = "Tanakan",
                    Code = "tnkn532",
                },
            };
        }

        private List<Person> GeneratePeople()
        {
            return new List<Person>
            {
                new Person
                {
                    Id = Guid.NewGuid(),
                    Address = "София, Люлин 10",
                    EGN = 8956231485,
                    FirstName = "Петър",
                    LastName = "Петров",
                },
                new Person
                {
                    Id = Guid.NewGuid(),
                    Address = "Варна, Широк Център",
                    EGN = 7852419658,
                    FirstName = "Николай",
                    LastName = "Колев",
                },
                new Person
                {
                    Id = Guid.NewGuid(),
                    Address = "Бургас, Крайморска 15",
                    EGN = 4587962321,
                    FirstName = "Георги",
                    LastName = "Пелев",
                },
            };
        }

        private List<Firm> GenerateFirms()
        {
            return new List<Firm>
            {
                new Firm
                {
                    Id = Guid.NewGuid(),
                    Address = "Пловдив, Под Тепетата 18",
                    EIK = 895623148,
                    Code = "ФБ895",
                    Name = "Фарма Бета"
                },
                new Firm
                {
                    Id = Guid.NewGuid(),
                    Address = "Велико Търново, Царевска 22",
                    EIK = 752419658,
                    Code = "СтФ752",
                    Name = "Стара Фарма"
                },
                new Firm
                {
                    Id = Guid.NewGuid(),
                    Address = "Плевен, Незнайна 90",
                    EIK = 458796321,
                    Code = "СвФ458",
                    Name = "Свръх Фарма"
                },
            };
        }

        private List<Delivery> GenerateDeliveries(List<Firm> firms)
        {
            return new List<Delivery>
            {
                new Delivery
                {
                    Id = Guid.NewGuid(),
                    Counterparty = firms[1],
                    CounterpartyId = firms[1].Id,
                    DoneAt = new DateTime(2018, 5, 10),
                    Number = 15236
                },
                new Delivery
                {
                    Id = Guid.NewGuid(),
                    Counterparty = firms[2],
                    CounterpartyId = firms[2].Id,
                    DoneAt = new DateTime(2019, 5, 10),
                    Number = 15333
                },
                new Delivery
                {
                    Id = Guid.NewGuid(),
                    Counterparty = firms[0],
                    CounterpartyId = firms[0].Id,
                    DoneAt = new DateTime(2017, 5, 10),
                    Number = 78523
                },
            };
        }

        private List<DeliveredMedicine> GenerateDeliveredMedicines(List<Delivery> deliveries, List<Medicine> medicines)
        {
            return new List<DeliveredMedicine>
            {
                new DeliveredMedicine
                {
                    Id = Guid.NewGuid(),
                    Delivery = deliveries[0],
                    DeliveryId = deliveries[0].Id,
                    Medicine = medicines[0],
                    MedicineId = medicines[0].Id,
                    Price = 12.56m,
                    Quantity = 100
                },
                new DeliveredMedicine
                {
                    Id = Guid.NewGuid(),
                    Delivery = deliveries[0],
                    DeliveryId = deliveries[0].Id,
                    Medicine = medicines[1],
                    MedicineId = medicines[1].Id,
                    Price = 1112.56m,
                    Quantity = 100
                },
                new DeliveredMedicine
                {
                    Id = Guid.NewGuid(),
                    Delivery = deliveries[0],
                    DeliveryId = deliveries[0].Id,
                    Medicine = medicines[2],
                    MedicineId = medicines[2].Id,
                    Price = 10.99m,
                    Quantity = 100
                },
                new DeliveredMedicine
                {
                    Id = Guid.NewGuid(),
                    Delivery = deliveries[1],
                    DeliveryId = deliveries[1].Id,
                    Medicine = medicines[3],
                    MedicineId = medicines[3].Id,
                    Price = 16.99m,
                    Quantity = 100
                },
                new DeliveredMedicine
                {
                    Id = Guid.NewGuid(),
                    Delivery = deliveries[1],
                    DeliveryId = deliveries[1].Id,
                    Medicine = medicines[1],
                    MedicineId = medicines[1].Id,
                    Price = 1000.56m,
                    Quantity = 100
                },
                new DeliveredMedicine
                {
                    Id = Guid.NewGuid(),
                    Delivery = deliveries[1],
                    DeliveryId = deliveries[1].Id,
                    Medicine = medicines[2],
                    MedicineId = medicines[2].Id,
                    Price = 10.99m,
                    Quantity = 100
                },
                new DeliveredMedicine
                {
                    Id = Guid.NewGuid(),
                    Delivery = deliveries[2],
                    DeliveryId = deliveries[2].Id,
                    Medicine = medicines[4],
                    MedicineId = medicines[4].Id,
                    Price = 8.99m,
                    Quantity = 100
                },
                new DeliveredMedicine
                {
                    Id = Guid.NewGuid(),
                    Delivery = deliveries[2],
                    DeliveryId = deliveries[2].Id,
                    Medicine = medicines[1],
                    MedicineId = medicines[1].Id,
                    Price = 1100.56m,
                    Quantity = 100
                },
                new DeliveredMedicine
                {
                    Id = Guid.NewGuid(),
                    Delivery = deliveries[2],
                    DeliveryId = deliveries[2].Id,
                    Medicine = medicines[3],
                    MedicineId = medicines[3].Id,
                    Price = 12.99m,
                    Quantity = 100
                },
            };
        }

        private List<Sell> GenerateSells(List<Person> people)
        {
            return new List<Sell>
            {
                new Sell
                {
                    Id = Guid.NewGuid(),
                    Counterparty = people[1],
                    CounterpartyId = people[1].Id,
                    DoneAt = new DateTime(2019, 5, 10),
                    Number = 15236
                },
                new Sell
                {
                    Id = Guid.NewGuid(),
                    Counterparty = people[2],
                    CounterpartyId = people[2].Id,
                    DoneAt = new DateTime(2019, 10, 10),
                    Number = 15333
                },
                new Sell
                {
                    Id = Guid.NewGuid(),
                    Counterparty = people[0],
                    CounterpartyId = people[0].Id,
                    DoneAt = new DateTime(2017, 7, 10),
                    Number = 78523
                },
            };
        }

        private List<SoldMedicine> GenerateSoldMedicines(List<Sell> sells, List<Medicine> medicines)
        {
            return new List<SoldMedicine>
            {
                new SoldMedicine
                {
                    Id = Guid.NewGuid(),
                    Sell = sells[0],
                    SellId = sells[0].Id,
                    Medicine = medicines[0],
                    MedicineId = medicines[0].Id,
                    Price = 15.99m,
                    Quantity = 10
                },
                new SoldMedicine
                {
                    Id = Guid.NewGuid(),
                    Sell = sells[0],
                    SellId = sells[0].Id,
                    Medicine = medicines[1],
                    MedicineId = medicines[1].Id,
                    Price = 1499.99m,
                    Quantity = 5
                },
                new SoldMedicine
                {
                    Id = Guid.NewGuid(),
                    Sell = sells[0],
                    SellId = sells[0].Id,
                    Medicine = medicines[2],
                    MedicineId = medicines[2].Id,
                    Price = 19.99m,
                    Quantity = 7
                },
                new SoldMedicine
                {
                    Id = Guid.NewGuid(),
                    Sell = sells[1],
                    SellId = sells[1].Id,
                    Medicine = medicines[3],
                    MedicineId = medicines[3].Id,
                    Price = 19.99m,
                    Quantity = 10
                },
                new SoldMedicine
                {
                    Id = Guid.NewGuid(),
                    Sell = sells[1],
                    SellId = sells[1].Id,
                    Medicine = medicines[1],
                    MedicineId = medicines[1].Id,
                    Price = 1999.99m,
                    Quantity = 50
                },
                new SoldMedicine
                {
                    Id = Guid.NewGuid(),
                    Sell = sells[1],
                    SellId = sells[1].Id,
                    Medicine = medicines[2],
                    MedicineId = medicines[2].Id,
                    Price = 19.99m,
                    Quantity = 7
                },
                new SoldMedicine
                {
                    Id = Guid.NewGuid(),
                    Sell = sells[2],
                    SellId = sells[2].Id,
                    Medicine = medicines[4],
                    MedicineId = medicines[4].Id,
                    Price = 12.99m,
                    Quantity = 100
                },
                new SoldMedicine
                {
                    Id = Guid.NewGuid(),
                    Sell = sells[2],
                    SellId = sells[2].Id,
                    Medicine = medicines[1],
                    MedicineId = medicines[1].Id,
                    Price = 1499.99m,
                    Quantity = 25
                },
                new SoldMedicine
                {
                    Id = Guid.NewGuid(),
                    Sell = sells[2],
                    SellId = sells[2].Id,
                    Medicine = medicines[3],
                    MedicineId = medicines[3].Id,
                    Price = 19.99m,
                    Quantity = 70
                },
            };
        }
    }
}