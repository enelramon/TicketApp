package com.ucne.ticketcompose.data.repository

import retrofit2.HttpException
import com.ucne.ticketcompose.data.remote.TicketApi
import com.ucne.ticketcompose.data.remote.dto.UsuarioDto
import com.ucne.ticketcompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class UsuarioRepository @Inject constructor(
    private val ticketApi: TicketApi
) {
    fun getUsuarios(): Flow<Resource<List<UsuarioDto>>> = flow {
        try {
            emit(Resource.Loading())

            val usuarios = ticketApi.getUsuarios()

            emit(Resource.Success(usuarios))

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