using System;
using System.Net.Http;
using System.Net.Http.Json;
using System.Threading.Tasks;

using System.Net.Http;
using TicketAppDotnet8.Models;

namespace TicketAppDotnet8.BLL
{
    public class MyCustomClient
    {
        private readonly HttpClient httpClient;

        public MyCustomClient(HttpClient httpClient)
        {
            this.httpClient = httpClient;
        }

        public async Task<List<TicketsDTO>> GetTicketsAsync()
        {
            return await httpClient.GetFromJsonAsync<List<TicketsDTO>>("api/Tickets");
        }

        public async Task<TicketsDTO> GetTicketByIdAsync(int ticketId)
        {
            return await httpClient.GetFromJsonAsync<TicketsDTO>($"api/Tickets/{ticketId}");
        }

        public async Task<int> AddTicketAsync(TicketsDTO ticket)
        {
            var response = await httpClient.PostAsJsonAsync("api/Tickets", ticket);
            response.EnsureSuccessStatusCode();
            return await response.Content.ReadFromJsonAsync<int>();
        }

        public async Task UpdateTicketAsync(int ticketId, TicketsDTO ticket)
        {
            var response = await httpClient.PutAsJsonAsync($"api/Tickets/{ticketId}", ticket);
            response.EnsureSuccessStatusCode();
        }

        public async Task DeleteTicketAsync(int ticketId)
        {
            var response = await httpClient.DeleteAsync($"api/Ticketss/{ticketId}");
            response.EnsureSuccessStatusCode();
        }
    }
}
