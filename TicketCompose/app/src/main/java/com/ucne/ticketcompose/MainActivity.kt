package com.ucne.ticketcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import com.ucne.ticketcompose.ui.theme.TicketComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var ticketDb: TicketDb
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TicketComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TicketScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun TicketScreen(
    viewModel: TicketViewModel = hiltViewModel()
) {
    val tickets by viewModel.tickets.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.isMessageShownFlow.collectLatest {
            if (it) {
                snackbarHostState.showSnackbar(
                    message = "Ticket guardado",
                    duration = SnackbarDuration.Short
                )
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Tickets") },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Refresh, contentDescription = "refresh"
                        )
                    }
                })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = "Ticket Details", style = MaterialTheme.typography.titleMedium)

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Cliente") },
                    singleLine = true,
                    value = viewModel.cliente,
                    onValueChange = {viewModel.cliente = it}
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Solicitado Por") },
                    singleLine = true,
                    value = viewModel.solicitadoPor,
                    onValueChange = {viewModel.solicitadoPor = it}
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Asunto") },
                    singleLine = true,
                    value = viewModel.asunto,
                    onValueChange = {viewModel.asunto = it}
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Solicitud") },
                    value = viewModel.solicitud,
                    onValueChange = {viewModel.solicitud = it},
                    maxLines = 5
                )
                val keyboardController = LocalSoftwareKeyboardController.current
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        keyboardController?.hide()
                        viewModel.saveTicket()
                        viewModel.setMessageShown()
                    })
                {
                    Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "guardar")
                    Text(text = "Guardar")
                }

            }

            Text(text = "Lista de Tickets", style = MaterialTheme.typography.titleMedium)
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(tickets){ ticket->
                    Text(text = ticket.asunto)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TicketScreenPreview() {
    TicketComposeTheme {
        TicketScreen()
    }
}


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

    val tickets:StateFlow<List<Ticket>> = ticketDb.ticketDao().getAll()
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

//region Room Database
@Entity(tableName = "Tickets")
data class Ticket(
    @PrimaryKey
    val ticketId: Int?=null ,
    var cliente: String = "",
    var solicitadoPor: String = "",
    var asunto: String = "",
    var solicitud: String = ""
)

@Dao
interface TicketDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(ticket: Ticket)

    @Query(
        """
        SELECT * 
        FROM Tickets 
        WHERE ticketId=:id  
        LIMIT 1
        """
    )
    suspend fun find(id: Int): Ticket

    @Delete
    suspend fun delete(ticket: Ticket)

    @Query("SELECT * FROM Tickets")
    fun getAll(): Flow<List<Ticket>>
}

@Database(
    entities = [Ticket::class],
    version = 2
)
abstract class TicketDb : RoomDatabase() {
    abstract fun ticketDao(): TicketDao
}
//endregion
