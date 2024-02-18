@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.ucne.ticketcompose.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ucne.ticketcompose.data.remote.dto.DocumentosDto
import com.ucne.ticketcompose.ui.documentos.DocumentosUiState
import com.ucne.ticketcompose.ui.documentos.DocumentosViewModel


@Composable
fun VentasListScreen(viewModel: DocumentosViewModel = hiltViewModel()) {

    val uiState: DocumentosUiState by viewModel.uiState.collectAsStateWithLifecycle()

    VentasListBody(uiState.documentos)
}
@Composable
fun VentasListBody(documentos: List<DocumentosDto>) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row( verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Outlined.Info, contentDescription = "information")
            Text(text = "Presione sobre un documento para reimprimirlo",
                style = MaterialTheme.typography.titleSmall)
        }

        Divider(color = Color.Black, thickness = 1.dp)

        Text(
            text = "Nombre del Cliente",
            style = MaterialTheme.typography.titleMedium
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "RNC",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Cantidad",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Total",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.End,
                modifier = Modifier.weight(1f)
            )
        }
        Divider(color = Color.Black, thickness = 1.dp)

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(documentos) { item ->
                VentaRow(item)
            }
        }

        Divider(color = Color.Black, thickness = 1.dp)
        //A row with space between

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Total: 10")
            Text(text = "5,000.00")
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VentaRow(item: DocumentosDto) {
    ElevatedCard(
        onClick = { }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )
        {
            Text(
                text = item.Rnc ,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = item.Rnc,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = item.Cantidad.toString(),
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = item.Total.toString(),
                    textAlign = TextAlign.End, modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

//create a preview for this composable function
@Preview(showSystemUi = true)
@Composable
fun VentasListScreenPreview() {
val documentos = listOf<DocumentosDto>(
    DocumentosDto( Numero = 1, Rnc ="131114776", Cantidad = 1.0, Total = 1000.00),
    DocumentosDto(Numero = 2, Rnc ="131114", Cantidad = 2.0, Total = 2000.00)
)
    VentasListBody(documentos)
}