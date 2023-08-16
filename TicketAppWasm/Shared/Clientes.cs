using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace TicketApp.Shared;

public class Clientes
{
    [Key]
    public int ClienteId { get; set; }
    [Required(ErrorMessage = "El Nombre es requerido")]
    public string Nombres { get; set; }
    [Required(ErrorMessage = "El Rnc es requerido")]
    public string Rnc { get; set; }
    public string Direccion { get; set; }
    public int LimiteCredito { get; set; }

    [ForeignKey("ClienteId")]
    public ICollection<ClientesDetalle> ClientesDetalle { get; set; } = new List<ClientesDetalle>();
}
