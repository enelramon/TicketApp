using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using TicketApp.Server.DAL;
using TicketApp.Shared;

namespace TicketApp.Server.Controllers;

[Route("api/[controller]")]
[ApiController]
public class TicketsController : ControllerBase
{
    private readonly TicketContext _context;

    public TicketsController(TicketContext context)
    {
        _context = context;
    }

    // GET: api/Tickets
    [HttpGet]
    public async Task<ActionResult<IEnumerable<Tickets>>> GetTickets()
    {
      if (_context.Tickets == null)
      {
          return NotFound();
      }
        return await _context.Tickets.ToListAsync();
    }

    // GET: api/Tickets/5
    [HttpGet("{id}")]
    public async Task<ActionResult<Tickets>> GetTicket(int id)
    {
      if (_context.Tickets == null)
      {
          return NotFound();
      }
        var tickets = await _context.Tickets.FindAsync(id);

        if (tickets == null)
        {
            return NotFound();
        }

        return tickets;
    }

    // POST: api/Tickets
    [HttpPost]
    public async Task<ActionResult<Tickets>> PostTicket(Tickets ticket)
    {
        if (!TicketsExists(ticket.TicketId) )
            _context.Tickets.Add(ticket);
        else
            _context.Tickets.Update(ticket);

        await _context.SaveChangesAsync();
        return Ok(ticket);
    }

    // DELETE: api/Tickets/5
    [HttpDelete("{id}")]
    public async Task<IActionResult> DeleteTickets(int id)
    {
        if (_context.Tickets == null)
        {
            return NotFound();
        }
        var tickets = await _context.Tickets.FindAsync(id);
        if (tickets == null)
        {
            return NotFound();
        }

        _context.Tickets.Remove(tickets);
        await _context.SaveChangesAsync();

        return NoContent();
    }

    private bool TicketsExists(int id)
    {
        return (_context.Tickets?.Any(e => e.TicketId == id)).GetValueOrDefault();
    }
}
