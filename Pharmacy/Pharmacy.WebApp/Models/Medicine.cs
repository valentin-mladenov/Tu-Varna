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
    }
}