using Microsoft.EntityFrameworkCore;
using System.Linq.Expressions;
using TicketAppDotnet8.DAL;
using TicketAppDotnet8.Models;

namespace TicketAppDotnet8.BLL;

    public class TicketsBLL
    {
    private Context _context;
    public TicketsBLL(Context context)
    {
        _context = context;
    }
    public bool Exists(int PrioridadId)
    {
        return _context.Tickets.Any(t => t.TicketId == PrioridadId);
    }

    public bool Save(Tickets tickets)
    {
        if (tickets.TicketId == 0)
            _context.Tickets.Add(tickets);
        else
            _context.Entry(tickets).State = EntityState.Modified;

        var saved = _context.SaveChanges() > 0;
        return saved;
    }
    public bool Delete(int id)
    {
        var ticket = _context.Tickets.Find(id);
        _context.Tickets.Remove(ticket);
        var deleted = _context.SaveChanges() > 0;
        return deleted;
    }
    public Tickets? Buscar(int TicketId)
    {
        return _context.Tickets
                .AsNoTracking()
                .SingleOrDefault(t => t.TicketId == TicketId);
    }
    public async Task<Tickets?> FindAsync(int id)
    {
        var ticket = await _context.Tickets.FindAsync(id);
        return ticket;
    }
    public List<Tickets> Listar(Expression<Func<Tickets, bool>> Criterio)
    {
        return _context.Tickets
                .Where(Criterio)
                .AsNoTracking()
                .ToList();
    }
    public List<Tickets> GetTickets()
    {
        var tickets = _context.Tickets.ToList();
        return tickets;
    }
}
