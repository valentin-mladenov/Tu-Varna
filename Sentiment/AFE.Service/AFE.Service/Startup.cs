using AFE.Model.Fabric;
using AFE.Service.Data;
using AFE.Service.Data.Repositories;
using AFE.Service.Data.Repositories.Interfaces;
using AFE.Service.Data.Services;
using AFE.Service.Data.Services.Interfaces;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Hosting;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;

using System.IO;

namespace AFE.Service
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            //services.AddRazorPages();

            services.AddDbContextPool<AFEContext>(
                options => options.UseSqlServer(Configuration.GetConnectionString("AfeDb"),
                providerOptions => providerOptions.CommandTimeout(30)));
            services.AddScoped<ICustomerFBRepository, CustomerFBRepository>();
            services.AddScoped<IMLInputFBRepository, MLInputFBRepository>();
            services.AddScoped<IPredictionRepository, PredictionRepository>();
            services.AddSingleton<MLModelBuilder>();

            services.AddCors(options =>
            {
                options.AddPolicy("policy",
                    builder =>
                    {
                        builder.WithOrigins("http://localhost:4200")
                            .AllowAnyHeader()
                            .AllowAnyMethod();
                    });
            });

            services.AddControllers();

            if (!File.Exists(Constants.ConsumeModelURL))
            {
                var serviceProvider = services.BuildServiceProvider();
                var mlFabric = serviceProvider.GetService<MLModelBuilder>();
                mlFabric.Init();
            }
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }
            else
            {
                // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
                app.UseHsts();
            }

            app.UseCors("policy");

            app.UseHttpsRedirection();

            app.UseRouting();

            app.UseEndpoints(e => {
                e.MapControllers();
            });
        }
    }
}
