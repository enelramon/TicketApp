using System.ComponentModel.DataAnnotations;

namespace TicketApp.Shared;

public class TiposTelefonos
{
    [Key]
    public int TipoId { get; set; }
    public string Descripcion { get; set; }
}
