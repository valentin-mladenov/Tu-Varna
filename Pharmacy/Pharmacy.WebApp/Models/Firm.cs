using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Pharmacy.WebApp.Models
{
    public class Firm : Counterparty
    {
        [Required]
        [Column(TypeName = "NVARCHAR")]
        public string Name { get; set; }

        [Required]
        [Column(TypeName = "NVARCHAR")]
        public string Code { get; set; }

        [Required]
        [Range(100000000, 999999999)]
        public long EIK { get; set; }

        public override string ToString()
        {
            return $"Name: {Name}, Code: {this.Code}, EIK: {this.EIK}, {base.ToString()}";
        }
    }
}