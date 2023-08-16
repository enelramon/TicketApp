using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using TicketApp.Shared;
using TicketApp.Server.DAL;

namespace ClienteApp.Server.Controllers;

[Route("api/[controller]")]
[ApiController]
public class TiposTelefonosController : ControllerBase
{
    private readonly TicketContext _context;

    public TiposTelefonosController(TicketContext context)
    {
        _context = context;
    }

    // GET: api/TiposTelefonos/5
    [HttpGet("{id}")]
    public async Task<ActionResult<TiposTelefonos>> GetTipoTelefono(int id)
    {
        var tipo = await _context.TiposTelefonos.FindAsync(id);

        return tipo!;
    }

    // GET: api/TiposTelefonos
    [HttpGet]
    public async Task<ActionResult<IEnumerable<TiposTelefonos>>> GetTiposTelefonos()
    {
        if (_context.TiposTelefonos == null)
        {
            return NotFound();
        }
        return await _context.TiposTelefonos.ToListAsync();
    }
}