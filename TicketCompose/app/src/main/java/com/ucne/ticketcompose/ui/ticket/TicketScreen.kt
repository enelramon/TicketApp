package com.ucne.ticketcompose.ui.ticket

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest

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
                val keyboardController = LocalSoftwareKeyboardController.current
                val focusManager = LocalFocusManager.current

                Text(text = "Ticket Details", style = MaterialTheme.typography.titleMedium)

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Cliente") },
                    singleLine = true,
                    maxLines=1,
                    value = viewModel.cliente,
                    onValueChange = viewModel::onClienteChanged,
                    leadingIcon = {
                        Icon(imageVector = Icons.Filled.Person, contentDescription = "person icon")
                    },
                    isError = viewModel.clienteError,
                    trailingIcon = {
                        AnimatedVisibility(
                            visible = viewModel.clienteError,
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            Icon(imageVector = Icons.Default.Warning, contentDescription = "")
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType =  KeyboardType.Text,
                        imeAction = ImeAction.Next,
                        capitalization = KeyboardCapitalization.Words
                    ),
                    keyboardActions = KeyboardActions{
                        focusManager.moveFocus(FocusDirection.Next)
                    }
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
                items(tickets) { ticket ->
                    Text(text = ticket.asunto)
                }
            }
        }
    }
}
