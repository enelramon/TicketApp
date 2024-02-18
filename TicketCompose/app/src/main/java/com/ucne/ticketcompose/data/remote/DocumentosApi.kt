package com.ucne.ticketcompose.data.remote

import com.ucne.ticketcompose.data.remote.dto.DocumentosDto
import retrofit2.http.GET

interface DocumentosApi {
    @GET("Documentos")
    suspend fun GetDocumentos(): List<DocumentosDto>
}