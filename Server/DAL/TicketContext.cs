using Microsoft.EntityFrameworkCore;
using TicketApp.Shared;

namespace TicketApp.Server.DAL;

public class TicketContext : DbContext
{
    public TicketContext(DbContextOptions<TicketContext> options) : base(options)
    {
    }

    public DbSet<Tickets> Tickets { get; set; }
}
