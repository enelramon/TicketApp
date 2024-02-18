package com.ucne.ticketcompose.data.remote

import com.squareup.moshi.Json
import retrofit2.http.GET

interface DocumentosApi {
    @GET("/Documentos")
    suspend fun GetDocumentos(): List<DocumentosDto>
}

data class DocumentosDto(
    val Numero: Int,
    val Rnc: String,
    val Cantidad: Double,
    @Json(name = "Monto")
    val Total:Double
)

/*
"Numero": 346012,
"Fecha": "2023-10-24T19:11:21",
"Tipo": 0,
"Rnc": "130409153",
"Ncf": "B0100055967",
"VencimientoNcf": "31/12/2024",
"CodigoCliente": 0,
"NombreCliente": "IMPORTADORA CONTINENTAL SRL",
"Telefono": null,
"TipoNCF": 1,
"Cantidad": 7.5415,
"Precio": 132.6,
"Descuento": 0,
"Monto": 1000,*/
