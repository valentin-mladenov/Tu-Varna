﻿//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace Pharmacy.WebApp.DbDiagram
{
    using System;
    using System.Data.Entity;
    using System.Data.Entity.Infrastructure;
    
    public partial class DbDiagram : DbContext
    {
        public DbDiagram()
            : base("name=DbDiagram")
        {
        }
    
        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            throw new UnintentionalCodeFirstException();
        }
    
        public virtual DbSet<C__MigrationHistory> C__MigrationHistory { get; set; }
        public virtual DbSet<Counterparty> Counterparties { get; set; }
        public virtual DbSet<DeliveredMedicine> DeliveredMedicines { get; set; }
        public virtual DbSet<Delivery> Deliveries { get; set; }
        public virtual DbSet<Medicine> Medicines { get; set; }
        public virtual DbSet<Sell> Sells { get; set; }
        public virtual DbSet<SoldMedicine> SoldMedicines { get; set; }
    }
}
