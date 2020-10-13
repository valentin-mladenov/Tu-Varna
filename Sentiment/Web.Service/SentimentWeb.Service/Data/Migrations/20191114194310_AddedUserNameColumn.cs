using Microsoft.EntityFrameworkCore.Migrations;

namespace SentimentWeb.Service.Data.Migrations
{
    public partial class AddedUserNameColumn : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<string>(
                name: "UserName",
                table: "CustomerFeedbacks",
                nullable: true);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "UserName",
                table: "CustomerFeedbacks");
        }
    }
}
