using System.ComponentModel.DataAnnotations;

namespace TicketAppDotnet8.Models
{
    public class TicketsDTO
    {
        public int IdTicket { get; set; }
        public DateTime Fecha { get; set; }
        public DateTime? Vence { get; set; }
        public int IdCliente { get; set; }
        public string? SolicitadoPor { get; set; }
        public string? Asunto { get; set; }

    }
}
