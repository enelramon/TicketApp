namespace BlazorServerExample.ViewModels;

using BlazorServerExample.Data;
using System.Threading.Tasks;

public class TicketsViewModel
{
    private Tickets _ticket = new Tickets();
    private string _mensaje = string.Empty;

    private readonly TicketsBLL _ticketBLL;
    public TicketsViewModel(TicketsBLL ticketBLL)
    {
        var t = new Tickets{
            TicketId=0,
            SolicitadoPor=""
        }
        this._ticketBLL = ticketBLL;
    }
    public Tickets Ticket
    {
        get { return _ticket; }
        set { _ticket = value; }
    }

    public string Mensaje
    {
        get { return _mensaje; }
        set { _mensaje = value; }
    }

    public void Buscar()
    {
        var encontrado =  _ticketBLL.Find(Ticket.TicketId); 
        if (encontrado != null)
            Ticket = encontrado;
        else
            Mensaje = "No se encontro el ticket";
    }

    public void Save()
    {
        var guardo =   _ticketBLL.Save(Ticket);
        if (guardo)
        {
            Mensaje = "Se guardo correctamente";
            Nuevo();
        }
        else
            Mensaje = "No se pudo guardar el ticket";
    }

    public void Nuevo()
    {
        Ticket = new Tickets();
        Mensaje = string.Empty;
    }

    public void Eliminar()
    {
        var eliminado = _ticketBLL.Delete(Ticket.TicketId);
        if (eliminado)
        {
            Mensaje = "Se elimino correctamente";
            Nuevo();
        }
        else
            Mensaje = "No se pudo eliminar el ticket";
    }
}

