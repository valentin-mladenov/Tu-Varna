//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace Car.Rental.Web.App.DbDiagram.UNI
{
    using System;
    using System.Collections.Generic;
    
    public partial class Student
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public Student()
        {
            this.TestAttempt = new HashSet<TestAttempt>();
        }
    
        public System.Guid Id { get; set; }
        public string FacultyNumber { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string Email { get; set; }
        public System.Guid ClassId { get; set; }
    
        public virtual Class Class { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<TestAttempt> TestAttempt { get; set; }
    }
}
