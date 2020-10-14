using Microsoft.EntityFrameworkCore.Migrations;

namespace SentimentWeb.Service.Data.Migrations
{
    public partial class AddUserDataToCustomerFeedback : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "Probability",
                table: "CustomerFeedbacks");

            migrationBuilder.DropColumn(
                name: "Score",
                table: "CustomerFeedbacks");

            migrationBuilder.DropColumn(
                name: "UserName",
                table: "CustomerFeedbacks");

            migrationBuilder.AddColumn<int>(
                name: "AgeRange",
                table: "CustomerFeedbacks",
                nullable: false,
                defaultValue: 0);

            migrationBuilder.AddColumn<string>(
                name: "ConfirmedLanguage",
                table: "CustomerFeedbacks",
                nullable: true);

            migrationBuilder.AddColumn<string>(
                name: "InternalLangScore",
                table: "CustomerFeedbacks",
                nullable: true);

            migrationBuilder.AddColumn<string>(
                name: "Language",
                table: "CustomerFeedbacks",
                nullable: true);

            migrationBuilder.AddColumn<int>(
                name: "MaritalStatus",
                table: "CustomerFeedbacks",
                nullable: false,
                defaultValue: 0);

            migrationBuilder.AddColumn<decimal>(
                name: "SentimentProbability",
                table: "CustomerFeedbacks",
                nullable: false,
                defaultValue: 0m);

            migrationBuilder.AddColumn<decimal>(
                name: "SentimentScore",
                table: "CustomerFeedbacks",
                nullable: false,
                defaultValue: 0m);

            migrationBuilder.AddColumn<int>(
                name: "Sex",
                table: "CustomerFeedbacks",
                nullable: false,
                defaultValue: 0);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "AgeRange",
                table: "CustomerFeedbacks");

            migrationBuilder.DropColumn(
                name: "ConfirmedLanguage",
                table: "CustomerFeedbacks");

            migrationBuilder.DropColumn(
                name: "InternalLangScore",
                table: "CustomerFeedbacks");

            migrationBuilder.DropColumn(
                name: "Language",
                table: "CustomerFeedbacks");

            migrationBuilder.DropColumn(
                name: "MaritalStatus",
                table: "CustomerFeedbacks");

            migrationBuilder.DropColumn(
                name: "SentimentProbability",
                table: "CustomerFeedbacks");

            migrationBuilder.DropColumn(
                name: "SentimentScore",
                table: "CustomerFeedbacks");

            migrationBuilder.DropColumn(
                name: "Sex",
                table: "CustomerFeedbacks");

            migrationBuilder.AddColumn<decimal>(
                name: "Probability",
                table: "CustomerFeedbacks",
                type: "decimal(18,2)",
                nullable: false,
                defaultValue: 0m);

            migrationBuilder.AddColumn<decimal>(
                name: "Score",
                table: "CustomerFeedbacks",
                type: "decimal(18,2)",
                nullable: false,
                defaultValue: 0m);

            migrationBuilder.AddColumn<string>(
                name: "UserName",
                table: "CustomerFeedbacks",
                type: "nvarchar(max)",
                nullable: true);
        }
    }
}
