using System;
using Microsoft.EntityFrameworkCore.Migrations;

namespace SentimentWeb.Service.Data.Migrations
{
    public partial class ChangeGUIDtoLong : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropPrimaryKey(
                name: "PK_MLInputFeedbacks",
                table: "MLInputFeedbacks");

            migrationBuilder.DropPrimaryKey(
                name: "PK_CustomerFeedbacks",
                table: "CustomerFeedbacks");

            migrationBuilder.DropColumn(
                name: "Id",
                table: "MLInputFeedbacks");

            migrationBuilder.DropColumn(
                name: "Id",
                table: "CustomerFeedbacks");

            migrationBuilder.AddColumn<long>(
                name: "Ident",
                table: "MLInputFeedbacks",
                nullable: false,
                defaultValue: 0L)
                .Annotation("SqlServer:Identity", "1, 1");

            migrationBuilder.AddColumn<string>(
                name: "Language",
                table: "MLInputFeedbacks",
                nullable: true);

            migrationBuilder.AddColumn<long>(
                name: "Ident",
                table: "CustomerFeedbacks",
                nullable: false,
                defaultValue: 0L)
                .Annotation("SqlServer:Identity", "1, 1");

            migrationBuilder.AddPrimaryKey(
                name: "PK_MLInputFeedbacks",
                table: "MLInputFeedbacks",
                column: "Ident");

            migrationBuilder.AddPrimaryKey(
                name: "PK_CustomerFeedbacks",
                table: "CustomerFeedbacks",
                column: "Ident");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropPrimaryKey(
                name: "PK_MLInputFeedbacks",
                table: "MLInputFeedbacks");

            migrationBuilder.DropPrimaryKey(
                name: "PK_CustomerFeedbacks",
                table: "CustomerFeedbacks");

            migrationBuilder.DropColumn(
                name: "Ident",
                table: "MLInputFeedbacks");

            migrationBuilder.DropColumn(
                name: "Language",
                table: "MLInputFeedbacks");

            migrationBuilder.DropColumn(
                name: "Ident",
                table: "CustomerFeedbacks");

            migrationBuilder.AddColumn<Guid>(
                name: "Id",
                table: "MLInputFeedbacks",
                type: "uniqueidentifier",
                nullable: false,
                defaultValueSql: "NEWID()");

            migrationBuilder.AddColumn<Guid>(
                name: "Id",
                table: "CustomerFeedbacks",
                type: "uniqueidentifier",
                nullable: false,
                defaultValue: new Guid("00000000-0000-0000-0000-000000000000"));

            migrationBuilder.AddPrimaryKey(
                name: "PK_MLInputFeedbacks",
                table: "MLInputFeedbacks",
                column: "Id");

            migrationBuilder.AddPrimaryKey(
                name: "PK_CustomerFeedbacks",
                table: "CustomerFeedbacks",
                column: "Id");
        }
    }
}
