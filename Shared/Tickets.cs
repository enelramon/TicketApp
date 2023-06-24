using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace TicketApp.Shared;


public class Tickets
{
    [Key]
    public int TicketId { get; set; }

    public DateTime Fecha { get; set; }  

    [Required(ErrorMessage = "Solicitado es requerido")]
    public string? SolicitadoPor { get; set; }

    [Required(ErrorMessage = "El Asunto es requerido")]
    public string? Asunto { get; set; }

    [Required(ErrorMessage = "La descripción es requerida")]
    public string? Descripcion { get; set; }

}