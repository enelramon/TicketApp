using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Options;
using TicketAppDotnet8.BLL;
using TicketAppDotnet8.Components;
using TicketAppDotnet8.DAL;
using TicketAppDotnet8.ViewModels;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddRazorComponents()
    .AddInteractiveServerComponents();

var ConStr = builder.Configuration.GetConnectionString("ConStr");
builder.Services.AddDbContextFactory<Context>(options =>
    options.UseSqlite(ConStr)
);
builder.Services.AddScoped<TicketsBLL>();
builder.Services.AddScoped<TicketsViewModel>();

builder.Configuration.GetSection("ApiSettings").Bind(builder.Configuration);

builder.Services.AddHttpClient<MyCustomClient>((services, client) =>
{
    var apiSettings = services.GetRequiredService<IOptions<ApiSettings>>();
    client.BaseAddress = new Uri(apiSettings.Value.BaseUrl);
    client.DefaultRequestHeaders.Add("X-API-Key", apiSettings.Value.ApiKey);
});

builder.Services.AddScoped<MyCustomClient>();


var app = builder.Build();

// Configure the HTTP request pipeline.
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Error", createScopeForErrors: true);
    // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
    app.UseHsts();
}

app.UseHttpsRedirection();

app.UseStaticFiles();
app.UseAntiforgery();

app.MapRazorComponents<App>()
    .AddInteractiveServerRenderMode();

app.Run();
