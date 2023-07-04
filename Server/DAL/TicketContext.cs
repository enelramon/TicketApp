using Microsoft.EntityFrameworkCore;
using TicketApp.Shared;

namespace TicketApp.Server.DAL;

public class TicketContext : DbContext
{
    public TicketContext(DbContextOptions<TicketContext> options)
        : base(options) { }
    public DbSet<Tickets> Tickets { get; set; }
    public DbSet<Clientes> Clientes { get; set; }
    public DbSet<TiposTelefonos> TiposTelefonos { get; set; }

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        base.OnModelCreating(modelBuilder);
        modelBuilder.Entity<TiposTelefonos>().HasData(new List<TiposTelefonos>()
        {
            new TiposTelefonos(){TipoId=1, Descripcion="Telefono"},
            new TiposTelefonos(){TipoId=2, Descripcion="Celular" },
            new TiposTelefonos(){TipoId=3, Descripcion="Oficina" }
        });
    }
}
