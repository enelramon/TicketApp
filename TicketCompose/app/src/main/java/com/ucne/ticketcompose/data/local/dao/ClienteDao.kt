package com.ucne.ticketcompose.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.ucne.ticketcompose.data.local.entities.Cliente
import kotlinx.coroutines.flow.Flow

@Dao
interface ClienteDao{
    @Upsert
    suspend fun Save(cliente: Cliente)

    @Delete
    suspend fun Delete(cliente: Cliente)

    @Query("""
        SELECT * 
        FROM Clientes
        WHERE clienteId=:id
        LIMIT 1
    """)
    suspend fun Find(id:Int): Cliente

    @Query("""
        SELECT *
        FROM Clientes
        ORDER BY Nombres
    """)
    fun getAll(): Flow<List<Cliente>>
}