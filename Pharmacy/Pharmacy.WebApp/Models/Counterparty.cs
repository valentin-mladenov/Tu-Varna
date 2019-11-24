using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

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

        public int CounterpartyType { get; set; }
    }
}