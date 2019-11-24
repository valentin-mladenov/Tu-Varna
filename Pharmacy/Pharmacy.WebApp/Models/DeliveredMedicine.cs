using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Pharmacy.WebApp.Models
{
    public class DeliveredMedicine
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

        [Required]
        public Guid DeliveryId { get; set; }
        public virtual Delivery Delivery { get; set; }
    }
}