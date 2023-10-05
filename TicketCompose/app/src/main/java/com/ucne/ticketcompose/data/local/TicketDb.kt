package com.ucne.ticketcompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ucne.ticketcompose.data.local.dao.ClienteDao
import com.ucne.ticketcompose.data.local.dao.TicketDao
import com.ucne.ticketcompose.data.local.entities.Cliente
import com.ucne.ticketcompose.data.local.entities.Ticket

@Database(
    entities = [Ticket::class, Cliente::class ],
    version = 4,
    exportSchema = false
)
abstract class TicketDb : RoomDatabase() {
    abstract fun ticketDao(): TicketDao
    abstract fun clienteDao(): ClienteDao
}