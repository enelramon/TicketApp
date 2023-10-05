package com.ucne.ticketcompose.ui.cliente

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ucne.ticketcompose.ui.theme.TicketComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClienteScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "Nombres",
            onValueChange = {})

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "Email",
            onValueChange = {})
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = "Telefono",
                onValueChange = {})

            Spacer(modifier = Modifier.width(8.dp))

            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = "Celular",
                onValueChange = {})
        }
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "Email",
            onValueChange = {})

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
            })
        {
            Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "guardar")
            Text(text = "Guardar")
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun ClienteScreenPreview() {
    TicketComposeTheme {
        ClienteScreen()
    }
}