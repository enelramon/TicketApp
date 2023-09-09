package com.ucne.ticketcompose.data.remote.dto

import com.squareup.moshi.Json

data class TicketDto (
    val cliente: String ,
    @Json(name = "solicitado")
    val solicitadoPor: String,
    val asunto: String ,
)