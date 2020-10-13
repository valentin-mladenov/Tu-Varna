using Microsoft.EntityFrameworkCore.Migrations;

namespace SentimentWeb.Service.Data.Migrations
{
    public partial class AddedConfirmedSentimentColumn : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<bool>(
                name: "ConfirmedSentiment",
                table: "CustomerFeedbacks",
                nullable: false,
                defaultValue: false);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "ConfirmedSentiment",
                table: "CustomerFeedbacks");
        }
    }
}
