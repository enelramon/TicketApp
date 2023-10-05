package com.ucne.ticketcompose.data.remote

import com.ucne.ticketcompose.data.remote.dto.TicketDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TicketApi {
    @GET("/tickets")
    suspend fun getTicket():List<TicketDto>

    @GET("/ticket/{ticketId}")
    suspend fun getTicketById(@Path("TicketId") ticketId: String): Response<TicketDto>

    @POST("/ticket")
    suspend fun postTicket(@Body ticket: TicketDto): Response<TicketDto>
}

