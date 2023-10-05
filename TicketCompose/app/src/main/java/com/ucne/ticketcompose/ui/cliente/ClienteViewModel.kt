package com.ucne.ticketcompose.ui.cliente

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.ticketcompose.data.local.entities.Cliente
import com.ucne.ticketcompose.data.repository.ClienteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClienteViewModel @Inject constructor(
    private val clienteRepository: ClienteRepository
) : ViewModel() {
   var nombres by mutableStateOf("")
   var email by mutableStateOf("")
   var telefono by mutableStateOf("")
   var celular by mutableStateOf("")
   var direccion by mutableStateOf("")

   fun save(){
       viewModelScope.launch {
           clienteRepository.save(Cliente(
               nombres = nombres,
               email = email,
               telefono = telefono,
               celular = celular,
               direccion = direccion
           ))
       }
   }
}