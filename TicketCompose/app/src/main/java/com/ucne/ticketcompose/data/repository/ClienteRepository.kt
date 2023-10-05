package com.ucne.ticketcompose.data.repository

import com.ucne.ticketcompose.data.local.dao.ClienteDao
import com.ucne.ticketcompose.data.local.entities.Cliente
import javax.inject.Inject

class ClienteRepository @Inject constructor(
    private val clienteDao: ClienteDao
) {
    suspend fun save(cliente: Cliente)= clienteDao.Save(cliente)

    suspend fun delete(cliente: Cliente)= clienteDao.Delete(cliente)

    suspend fun find(id: Int) = clienteDao.Find(id)

    fun getAll()= clienteDao.getAll()
}