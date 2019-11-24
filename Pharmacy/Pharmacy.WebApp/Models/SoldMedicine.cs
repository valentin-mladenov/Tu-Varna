using System;
using System.ComponentModel.DataAnnotations;

namespace Pharmacy.WebApp.Models
{
    public class SoldMedicine
    {
        [Key]
        public Guid Id { get; set; }

        [Required]
        public decimal Price { get; set; }

        [Required]
        public decimal Quantity { get; set; }

        [Required]
        public Guid MedicineId { get; set; }
        public virtual Medicine Medicine { get; set; }
    }
}