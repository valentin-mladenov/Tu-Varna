﻿using System.IO;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Hosting;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;

using LanguageML.Model;
using SentimentML.Model;
using SentimentWeb.Service.Data;
using SentimentWeb.Service.Data.Repositories;
using SentimentWeb.Service.Data.Repositories.Interfaces;
using SentimentWeb.Service.Data.Services;
using SentimentWeb.Service.Data.Services.Interfaces;

namespace SentimentWeb.Service
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
            services.AddDbContextPool<SentimentWebContext>(
                options => options.UseSqlServer(Configuration.GetConnectionString("SentimentDb"),
                providerOptions => providerOptions.CommandTimeout(30)));

            services.AddScoped<ICustomerFBRepository, CustomerFBRepository>();
            services.AddScoped<IMLInputFBRepository, MLInputFBRepository>();
            services.AddScoped<IPredictionService, PredictionService>();
            services.AddSingleton<MLModelBuilder>();

            services.AddCors(options => {
                options.AddPolicy("policy",
                    builder => {
                        builder.WithOrigins("http://localhost:4200")
                            .AllowAnyHeader()
                            .AllowAnyMethod();
                    });
            });

            services.AddControllers();

            if (!File.Exists(MLModelBuilder.GetAbsolutePath(SentimentML.Constants.ModelFilePath))) {
                var serviceProvider = services.BuildServiceProvider();
                var mlFabric = serviceProvider.GetService<MLModelBuilder>();
                mlFabric.InitSQL();
            }

            if (!File.Exists(LanguageMLModelBuilder.GetAbsolutePath(Constants.ModelFilePath))) {
                LanguageMLModelBuilder.InitSQL();
            }
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment()) {
                app.UseDeveloperExceptionPage();
            }
            else {
                app.UseHsts();
            }

            app.UseCors("policy")
                .UseHttpsRedirection()
                .UseRouting()
                .UseEndpoints(e => {
                    e.MapControllers();
                });
        }
    }
}
