package com.ucne.ticketcompose.data.repository

import com.ucne.ticketcompose.data.remote.DocumentosApi
import com.ucne.ticketcompose.data.remote.dto.DocumentosDto
import com.ucne.ticketcompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DocumentosRepository @Inject constructor(
    private val documentosApi: DocumentosApi
) {
    fun GetDocumentos():Flow<Resource<List<DocumentosDto>>> = flow {
        try {
            emit(Resource.Loading())

            val documentos = documentosApi.GetDocumentos()

            emit(Resource.Success(documentos))
        }
        catch (e:HttpException){
            emit(Resource.Error(e.message?:"hay un error"))
        }
        catch (e:IOException){
            emit(Resource.Error(e.message?:"hay un error de internet"))
        }
    }
}