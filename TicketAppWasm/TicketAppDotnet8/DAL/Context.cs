using Microsoft.EntityFrameworkCore;
using TicketAppDotnet8.Models;

namespace TicketAppDotnet8.DAL
{
    public class  Context : DbContext
    {
        public Context(DbContextOptions<Context> options)
        : base(options) { }
        public DbSet<Tickets> Tickets { get; set; }

    }
}
