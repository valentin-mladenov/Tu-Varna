using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Design;
using Microsoft.Extensions.Configuration;


namespace SentimentWeb.Service.Data
{
    public class SentimentWebContextDesignTimeDbContextFactory : IDesignTimeDbContextFactory<SentimentWebContext>
    {
        public SentimentWebContext CreateDbContext(string[] args)
        { 

            var optionsBuilder = new DbContextOptionsBuilder<SentimentWebContext>();

            IConfigurationRoot configuration = new ConfigurationBuilder()
                    .SetBasePath(
                        AppDomain.CurrentDomain.BaseDirectory.Substring(0, AppContext.BaseDirectory.IndexOf("bin")))
                    .AddJsonFile("appsettings.json")
                    .AddJsonFile($"appsettings.{Environment.MachineName}.json", true, true)
                    .AddEnvironmentVariables()
                    .Build();

            optionsBuilder.UseSqlServer(configuration.GetConnectionString($"AfeDb"));
            return new SentimentWebContext(optionsBuilder.Options);
        }
    }
}
