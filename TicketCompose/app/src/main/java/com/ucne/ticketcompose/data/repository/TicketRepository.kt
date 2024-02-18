package com.ucne.ticketcompose.data.repository

import com.ucne.ticketcompose.data.local.TicketDb
import com.ucne.ticketcompose.data.local.entities.Ticket
import com.ucne.ticketcompose.data.remote.TicketApi
import com.ucne.ticketcompose.data.remote.dto.TicketResponse
import com.ucne.ticketcompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TicketRepository @Inject constructor(
    private val ticketDb: TicketDb,
    private val ticketApi: TicketApi
) {
    suspend fun  guardarTicket(ticket: Ticket) = ticketDb.ticketDao().save(ticket)
    fun getAll() = ticketDb.ticketDao().getAll()

    fun getTickets(selectedUser: Int?): Flow<Resource<List<TicketResponse>>> = flow {
        try {
            emit(Resource.Loading())

            val tickets = ticketApi.getTickets(selectedUser)

            emit(Resource.Success(tickets))

        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
        catch (e: Exception) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
}

