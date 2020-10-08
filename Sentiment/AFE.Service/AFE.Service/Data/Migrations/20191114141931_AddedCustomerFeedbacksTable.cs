using System;
using Microsoft.EntityFrameworkCore.Migrations;

namespace AFE.Service.Data.Migrations
{
    public partial class AddedCustomerFeedbacksTable : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AlterColumn<Guid>(
                name: "Id",
                table: "MLInputFeedbacks",
                nullable: false,
                defaultValueSql: "NEWID()",
                oldClrType: typeof(Guid),
                oldDefaultValueSql: "NEWID();");

            migrationBuilder.CreateTable(
                name: "CustomerFeedbacks",
                columns: table => new
                {
                    Id = table.Column<Guid>(nullable: false),
                    Text = table.Column<string>(nullable: true),
                    Sentiment = table.Column<bool>(nullable: false),
                    Probability = table.Column<decimal>(nullable: false),
                    Score = table.Column<decimal>(nullable: false),
                    SentToML = table.Column<bool>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_CustomerFeedbacks", x => x.Id);
                });
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "CustomerFeedbacks");

            migrationBuilder.AlterColumn<Guid>(
                name: "Id",
                table: "MLInputFeedbacks",
                nullable: false,
                defaultValueSql: "NEWID();",
                oldClrType: typeof(Guid),
                oldDefaultValueSql: "NEWID()");
        }
    }
}
