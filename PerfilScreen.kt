import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RegistroPerfilScreen(onContinuar: (String) -> Unit) {
    var seleccion by remember { mutableStateOf("Quincenal") }
    var expanded by remember { mutableStateOf(false) }
    val opciones = listOf("Diario", "Semanal", "Quincenal", "Mensual")

    Column(modifier = Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Configura tu Pago", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Box {
            OutlinedButton(onClick = { expanded = true }, modifier = Modifier.fillMaxWidth()) {
                Text("Frecuencia: $seleccion")
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                opciones.forEach { opcion ->
                    DropdownMenuItem(text = { Text(opcion) }, onClick = { seleccion = opcion; expanded = false })
                }
            }
        }
        Button(onClick = { onContinuar(seleccion) }, modifier = Modifier.padding(top = 32.dp).fillMaxWidth()) {
            Text("Siguiente")
        }
    }
}