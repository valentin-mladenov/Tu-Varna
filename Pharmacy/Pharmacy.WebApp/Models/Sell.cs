using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Pharmacy.WebApp.Models
{
    public class Sell
    {
        public Sell()
        {
            this.SoldMedicines = new HashSet<SoldMedicine>();
        }

        public virtual ICollection<SoldMedicine> SoldMedicines { get; set; }

        [Key]
        public Guid Id { get; set; }

        [Required]
        [Index(IsUnique = true)]
        [Range(1, 9999999)]
        public int Number { get; set; }

        [DataType(DataType.Date)]
        [DisplayFormat(DataFormatString = "{0:yyyy-MM-dd}", ApplyFormatInEditMode = true)]
        [Column(TypeName = "DateTime2")]
        public DateTime DoneAt { get; set; }

        [Required]
        public Guid CounterpartyId { get; set; }
        public virtual Counterparty Counterparty { get; set; }
    }
}