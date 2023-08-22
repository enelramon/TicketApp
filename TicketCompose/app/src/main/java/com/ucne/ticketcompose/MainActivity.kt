package com.ucne.ticketcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ucne.ticketcompose.ui.theme.TicketComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicketComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FormTicket()
                }
            }
        }
    }
}

// Clase Ticket
data class Ticket(
    var client: String = "",
    var requestBy: String = "",
    var reason: String = "",
    var request: String = ""
)

// Comp Ticket Form
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormTicket() {
    var client : String by remember {
        mutableStateOf("")
    }
    var requestBy : String by remember {
        mutableStateOf("")
    }
    var reason : String by remember {
        mutableStateOf("")
    }
    var request : String by remember {
        mutableStateOf("")
    }
    var listTickets by remember { mutableStateOf(mutableListOf(Ticket()))}

    Column (modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .shadow(16.dp),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(25.dp)
            ) {
                Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start){
                    Text(text = "#000000", color = Color.Gray)
                }
                Text(
                    text = "TicketsAPP",
                    modifier = Modifier.padding(40.dp),
                    fontSize = 25.sp,
                    fontStyle = FontStyle.Normal,
                    color = Color.Black
                )
                Divider()
                TextField(
                    value = client,
                    onValueChange = { client = it },
                    modifier = Modifier.padding(25.dp),
                    label = { Text("Cliente:") }
                )
                TextField(
                    value = requestBy,
                    onValueChange = { requestBy = it },
                    modifier = Modifier.padding(25.dp),
                    label = { Text("Solicitado Por:") }
                )
                TextField(
                    value = reason,
                    onValueChange = { reason = it },
                    modifier = Modifier.padding(25.dp),
                    label = { Text("Asunto") }
                )
                TextField(
                    value = request,
                    onValueChange = { request = it },
                    modifier = Modifier.padding(25.dp),
                    label = { Text("Solicitud") }
                )
                Spacer(modifier = Modifier.size(25.dp))
                Divider()
                Spacer(modifier = Modifier.size(25.dp))
            }
        }
        BottomAppBar {
            Button(onClick = {
                listTickets.add(Ticket(client=client,requestBy=requestBy,reason=reason,request=request))
                client=""
                request=""
                reason=""
                requestBy=""
                println(listTickets[listTickets.count()-1])
            }, modifier =  Modifier.fillMaxSize(), shape = RectangleShape) {
                Text(text = "Solicitar")
            }
        }
    }

}
