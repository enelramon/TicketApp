package com.ucne.ticketcompose.ui.ticket

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.ticketcompose.data.local.TicketDb
import com.ucne.ticketcompose.data.local.entities.Ticket
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TicketViewModel @Inject constructor(
    private val ticketDb: TicketDb
) : ViewModel() {

    var cliente by mutableStateOf("")
    var solicitadoPor by mutableStateOf("")
    var asunto by mutableStateOf("")
    var solicitud by mutableStateOf("")

    private val _isMessageShown = MutableSharedFlow<Boolean>()
    val isMessageShownFlow = _isMessageShown.asSharedFlow()

    fun setMessageShown() {
        viewModelScope.launch {
            _isMessageShown.emit(true)
        }
    }

    val tickets: StateFlow<List<Ticket>> = ticketDb.ticketDao().getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun saveTicket() {
        viewModelScope.launch {
            val ticket = Ticket(
                cliente = cliente,
                solicitadoPor = solicitadoPor,
                asunto = asunto,
                solicitud = solicitud
            )
            ticketDb.ticketDao().save(ticket)
            limpiar()
        }
    }

    private fun limpiar() {
        cliente=""
        solicitadoPor =""
        asunto =""
        solicitud =""
    }
}