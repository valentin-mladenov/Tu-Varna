using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;

namespace Pharmacy.WebApp.Models
{
    public class Medicine
    {
        public Medicine()
        {
            this.SoldMedicines = new HashSet<SoldMedicine>();
            this.DeliveredMedicines = new HashSet<DeliveredMedicine>();
        }

        public virtual ICollection<DeliveredMedicine> DeliveredMedicines { get; set; }
        public virtual ICollection<SoldMedicine> SoldMedicines { get; set; }

        [Key]
        public Guid Id { get; set; }

        [Required]
        [Index(IsUnique = true)]
        [Column(TypeName = "NVARCHAR")]
        public string Name { get; set; }

        [Required]
        [Index(IsUnique = true)]
        [Column(TypeName = "NVARCHAR")]
        public string Code { get; set; }

        [Required]
        public decimal AvailableQuantity
        {
            get
            {
                return this.DeliveredMedicines.Select(dm => dm.Quantity).Sum() -
                    this.SoldMedicines.Select(sm => sm.Quantity).Sum();
            }
            private set { }
        }

        [NotMapped]
        public decimal SoldMedicineCount
        {
            get
            {
                return this.SoldMedicines.Select(sm => sm.Quantity).Sum();
            }
        }

        [NotMapped]
        public decimal DeliveredMedicineCount
        {
            get
            {
                return this.DeliveredMedicines.Select(sm => sm.Quantity).Sum();
            }
        }

        [NotMapped]
        public decimal TotalSoldMedicineSum
        {
            get
            {
                return this.SoldMedicines.Select(sm => sm.Quantity * sm.Price).Sum();
            }
        }

        [NotMapped]
        public decimal TotalDeliveryMedicineSum
        {
            get
            {
                return this.DeliveredMedicines.Select(sm => sm.Quantity * sm.Price).Sum();
            }
        }

        [NotMapped]
        public decimal TotalEarnedSum
        {
            get
            {
                return this.TotalSoldMedicineSum - this.TotalDeliveryMedicineSum;
            }
        }

        [NotMapped]
        public decimal CheapestDelivered
        {
            get
            {
                return this.DeliveredMedicines.Min(sm => sm.Price);
            }
        }

        [NotMapped]
        public string CheapestDeliveredSupplier
        {
            get
            {
                return this.DeliveredMedicines
                    .Where(dm => dm.Price == this.CheapestDelivered)
                    .FirstOrDefault()
                    .Delivery.Counterparty.ToString();
            }
        }

        public override string ToString()
        {
            return $"Name: {Name}, Code: {Code}, AvailableQuantity: {AvailableQuantity}";
        }
    }
}