using System.Data.Entity;
using System.Data.Entity.ModelConfiguration.Conventions;
namespace Pharmacy.WebApp.Models.DataAccessLayer
{
    public class PharmacyDbContext : DbContext
    {
        public PharmacyDbContext() : base("Name=PharmacyDBConnectionString")
        {
            Database.SetInitializer(new PharmacyInitializer());
        }

        public DbSet<Sell> Sells { get; set; }

        public DbSet<SoldMedicine> SoldMedicines { get; set; }

        public DbSet<Delivery> Deliveries { get; set; }

        public DbSet<DeliveredMedicine> DeliveredMedicines { get; set; }

        public DbSet<Medicine> Medicines { get; set; }

        public DbSet<Person> Persons { get; set; }

        public DbSet<Firm> Firms { get; set; }

        public DbSet<Counterparty> Counterparties { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Counterparty>().ToTable("Counterparty");

            modelBuilder.Conventions.Remove<PluralizingTableNameConvention>();
        }

    }
}