package com.ucne.ticketcompose.data.remote

import com.ucne.ticketcompose.data.remote.dto.TicketDto
import com.ucne.ticketcompose.data.remote.dto.TicketResponse
import com.ucne.ticketcompose.data.remote.dto.UsuarioDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface TicketApi {
    @GET("/tickets")
    @Headers("X-API-Key: test")
    suspend fun getTickets(@Query("idEncargado") idEncargado: Int?):List<TicketResponse>

    @GET("/ticket/{ticketId}")
    @Headers("X-API-Key: test")
    suspend fun getTicketById(@Path("TicketId") ticketId: String): Response<TicketDto>

    @POST("/ticket")
    @Headers("X-API-Key: test")
    suspend fun postTicket(@Body ticket: TicketDto): Response<TicketDto>

    @GET("/Usuarios")
    @Headers("X-API-Key: test")
    suspend fun getUsuarios():List<UsuarioDto>
}

