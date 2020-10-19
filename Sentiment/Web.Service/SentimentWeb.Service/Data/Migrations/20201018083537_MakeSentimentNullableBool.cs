using Microsoft.EntityFrameworkCore.Migrations;

namespace SentimentWeb.Service.Data.Migrations
{
    public partial class MakeSentimentNullableBool : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AlterColumn<bool>(
                name: "Sentiment",
                table: "MLInputFeedbacks",
                nullable: true,
                oldClrType: typeof(bool),
                oldType: "bit");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AlterColumn<bool>(
                name: "Sentiment",
                table: "MLInputFeedbacks",
                type: "bit",
                nullable: false,
                oldClrType: typeof(bool),
                oldNullable: true);
        }
    }
}
