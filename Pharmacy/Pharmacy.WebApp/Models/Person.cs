using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Pharmacy.WebApp.Models
{
    public class Person : Counterparty
    {
        [Required]
        [Index(IsUnique = true)]
        [Column(TypeName = "NVARCHAR")]
        public string FirstName { get; set; }

        [Required]
        [Index(IsUnique = true)]
        [Column(TypeName = "NVARCHAR")]
        public string LastName { get; set; }

        [Required]
        [Index(IsUnique = true)]
        [Range(1000000000, 9999999999)]
        public long EGN { get; set; }

        public override string ToString()
        {
            return $"Name: {FirstName} {LastName}, EGN: {EGN}, {base.ToString()}";
        }
    }
}