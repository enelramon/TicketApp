package com.ucne.ticketcompose.data.repository

import com.ucne.ticketcompose.data.local.TicketDb
import com.ucne.ticketcompose.data.local.entities.Ticket
import javax.inject.Inject

class TicketRepository @Inject constructor(
    private val ticketDb: TicketDb
) {
    suspend fun  guardarTicket(ticket: Ticket) = ticketDb.ticketDao().save(ticket)
    fun getAll() = ticketDb.ticketDao().getAll()
}