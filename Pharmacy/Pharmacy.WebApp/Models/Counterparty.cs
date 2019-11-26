using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;

namespace Pharmacy.WebApp.Models
{
    public abstract class Counterparty
    {
        public Counterparty()
        {
            this.Deliveries = new HashSet<Delivery>();
            this.Sales = new HashSet<Sell>();
        }

        public virtual ICollection<Delivery> Deliveries { get; set; }

        public virtual ICollection<Sell> Sales { get; set; }

        [Key]
        public Guid Id { get; set; }

        [Required]
        [Index(IsUnique = true)]
        [MinLength(10)]
        [Column(TypeName = "NVARCHAR")]
        public string Address { get; set; }

        [NotMapped]
        public int SalesCount
        {
            get
            {
                return this.Sales.Count;
            }
        }

        [NotMapped]
        public decimal MoneyFromSales
        {
            get
            {
                return this.Sales.Select(s => s.SoldMedicines.Select(sm => sm.Quantity * sm.Price).Sum()).Sum();
            }
        }

        [NotMapped]
        public string Stringed
        {
            get
            {
                return this.ToString();
            }
            private set { }
        }

        public override string ToString()
        {
            return $"Address: {Address}";
        }
    }
}