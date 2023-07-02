using Microsoft.EntityFrameworkCore;
using TicketApp.Server.DAL;
using TicketApp.Shared;

namespace TicketApp.Server.Services;

public class ClienteService
{
    private readonly TicketContext _context;

    public ClienteService(TicketContext context)
    {
        _context = context;
    }
     
    public async Task<bool> Guardar(Clientes cliente)
    {
        if (!ClientesExists(cliente.ClienteId))
            _context.Clientes.Add(cliente);
        else
            _context.Clientes.Update(cliente);

        var cantidad =await _context.SaveChangesAsync();
        return cantidad > 0;
    }

    private bool ClientesExists(int id)
    {
        return (_context.Clientes?.Any(e => e.ClienteId == id)).GetValueOrDefault();
    }
}
