using SentimentWeb.Service.Data.Entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System;
using System.ComponentModel.DataAnnotations.Schema;

namespace SentimentWeb.Service.Data
{
	public class SentimentWebContext : DbContext
	{
		public SentimentWebContext(DbContextOptions<SentimentWebContext> options) : base(options) { }

		public Guid TenantId { get; set; }

		public DbSet<MLInputFeedback> MLInputFeedbacks { get; set; }
		public DbSet<CustomerFeedback> CustomerFeedbacks { get; set; }


		protected override void OnModelCreating(ModelBuilder builder)
		{
			builder.Entity<MLInputFeedback>().Property(f => f.Id).HasDefaultValueSql("NEWID()");
		}
	}
}
