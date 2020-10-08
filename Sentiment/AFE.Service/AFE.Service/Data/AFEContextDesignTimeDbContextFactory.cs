using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Design;
using Microsoft.Extensions.Configuration;


namespace AFE.Service.Data
{
    public class AFEContextDesignTimeDbContextFactory : IDesignTimeDbContextFactory<AFEContext>
    {
        public AFEContext CreateDbContext(string[] args)
        { 

            var optionsBuilder = new DbContextOptionsBuilder<AFEContext>();

            IConfigurationRoot configuration = new ConfigurationBuilder()
                    .SetBasePath(
                        AppDomain.CurrentDomain.BaseDirectory.Substring(0, AppContext.BaseDirectory.IndexOf("bin")))
                    .AddJsonFile("appsettings.json")
                    .AddJsonFile($"appsettings.{Environment.MachineName}.json", true, true)
                    .AddEnvironmentVariables()
                    .Build();

            optionsBuilder.UseSqlServer(configuration.GetConnectionString($"AfeDb"));
            return new AFEContext(optionsBuilder.Options);
        }
    }
}
