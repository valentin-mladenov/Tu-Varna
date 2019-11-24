using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Pharmacy.WebApp.Models
{
    public class Firm : Counterparty
    {
        [Required]
        [Index(IsUnique = true)]
        [Column(TypeName = "NVARCHAR")]
        public string Name { get; set; }

        [Required]
        [Index(IsUnique = true)]
        [Column(TypeName = "NVARCHAR")]
        public string Code { get; set; }

        [Required]
        [Index(IsUnique = true)]
        [Range(100000000, 999999999)]
        public int EIK { get; set; }

        public override string ToString()
        {
            return $"Name: {Name}, Code: {this.Code}, EIK: {this.EIK}, {base.ToString()}";
        }
    }
}