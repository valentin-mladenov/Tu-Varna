﻿// <auto-generated />
using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.EntityFrameworkCore.Migrations;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;

namespace SentimentWeb.Service.Data.Migrations
{
    [DbContext(typeof(SentimentWebContext))]
    [Migration("20191114160515_AddedConfirmedSentimentColumn")]
    partial class AddedConfirmedSentimentColumn
    {
        protected override void BuildTargetModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("ProductVersion", "2.2.4-servicing-10062")
                .HasAnnotation("Relational:MaxIdentifierLength", 128)
                .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

            modelBuilder.Entity("SentimentWeb.Service.Data.Entities.CustomerFeedback", b =>
                {
                    b.Property<Guid>("Id")
                        .ValueGeneratedOnAdd();

                    b.Property<bool>("ConfirmedSentiment");

                    b.Property<decimal>("Probability");

                    b.Property<decimal>("Score");

                    b.Property<bool>("SentToML");

                    b.Property<bool>("Sentiment");

                    b.Property<string>("Text");

                    b.HasKey("Id");

                    b.ToTable("CustomerFeedbacks");
                });

            modelBuilder.Entity("SentimentWeb.Service.Data.Entities.MLInputFeedback", b =>
                {
                    b.Property<Guid>("Id")
                        .ValueGeneratedOnAdd()
                        .HasDefaultValueSql("NEWID()");

                    b.Property<bool>("Sentiment");

                    b.Property<string>("Text");

                    b.HasKey("Id");

                    b.ToTable("MLInputFeedbacks");
                });
#pragma warning restore 612, 618
        }
    }
}