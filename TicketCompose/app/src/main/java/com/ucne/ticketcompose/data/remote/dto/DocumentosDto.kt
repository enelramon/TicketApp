package com.ucne.ticketcompose.data.remote.dto

import com.squareup.moshi.Json

data class DocumentosDto(
    val Numero: Int,
    val Rnc: String,
    val Cantidad: Double,
    @Json(name = "Monto")
    val Total:Double
)