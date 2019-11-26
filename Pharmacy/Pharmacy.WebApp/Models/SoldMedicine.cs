using System;
using System.ComponentModel.DataAnnotations;

namespace Pharmacy.WebApp.Models
{
    public class SoldMedicine
    {
        [Key]
        public Guid Id { get; set; }

        [Required]
        [Range(typeof(decimal), "0", "10000000")]
        public decimal Price { get; set; }

        [Required]
        [Range(typeof(decimal), "0", "10000000")]
        public decimal Quantity { get; set; }

        [Required]
        public Guid MedicineId { get; set; }
        public virtual Medicine Medicine { get; set; }

        [Required]
        public Guid SellId { get; set; }
        public virtual Sell Sell { get; set; }
    }
}