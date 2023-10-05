package com.ucne.ticketcompose.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Clientes")
data class Cliente(
    @PrimaryKey
    val clienteId: Int? = null,
    var nombres: String = "",
    var email: String = "",
    var telefono: String = "",
    var celular: String = "",
    var direccion: String = ""
)