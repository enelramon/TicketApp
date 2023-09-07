using Microsoft.EntityFrameworkCore;
using System.ComponentModel.DataAnnotations;

namespace BlazorServerExample.Data;

//Instalar entityframework 
// dotnet add package Microsoft.EntityFrameworkCore.Sqlite
// dotnet add package Microsoft.EntityFrameworkCore.Tools
//Crear el modelo
public class Tickets
{
    [Key]
    public int TicketId { get; set; }
    [Required(ErrorMessage = "El campo {0} es requerido")]
    public string Cliente { get; set; }
    [Required(ErrorMessage = "El campo {0} es requerido")]
    public string SolicitadoPor { get; set; }
    public string Asunto { get; set; }
    public string Solicitud { get; set; }
}

//Crear el contexto
public class Contexto : DbContext
{
    public Contexto(DbContextOptions<Contexto> options) : base(options)
    {
    }

    public DbSet<Tickets> Tickets { get; set; }
}

//crear la bll
public class TicketsBLL
{
    private readonly Contexto _contexto;
    public TicketsBLL(Contexto contexto)
    {
        _contexto = contexto;
    }

    public bool Save(Tickets ticket)
    {
        if (ticket.TicketId == 0)
            _contexto.Tickets.Add(ticket);
        else
            _contexto.Entry(ticket).State = EntityState.Modified;

        var saved = _contexto.SaveChanges() > 0;
        return saved;
    }

    public async Task<Tickets?> FindAsync(int id)
    {
        var ticket = await _contexto.Tickets.FindAsync(id);
        return ticket;
    }

    public bool Delete(int id)
    {
        var ticket = _contexto.Tickets.Find(id);
        _contexto.Tickets.Remove(ticket);
        var deleted = _contexto.SaveChanges() > 0;
        return deleted;
    }
    //get all
    public List<Tickets> GetTickets()
    {
        var tickets = _contexto.Tickets.ToList();
        return tickets;
    }
}