using System.ComponentModel.DataAnnotations;

namespace TicketApp.Shared;

public class ClientesDetalle
{
    [Key]
    public int DetalleId { get; set; }
    public int ClienteId { get; set; }
    public int TipoId { get; set; }
    public string Telefono { get; set; }
}