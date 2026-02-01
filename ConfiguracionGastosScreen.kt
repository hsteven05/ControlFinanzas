import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ConfiguracionGastosScreen(frecuencia: String, onFinalizar: (List<GastoFijo>) -> Unit) {
    val totalPeriodos = if (frecuencia == "Quincenal") 2 else 1
    var periodoActual by remember { mutableIntStateOf(1) }
    val listaTemp = remember { mutableStateListOf<GastoFijo>() }
    var titulo by remember { mutableStateOf("") }
    var valor by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Lista de Gastos - Parte $periodoActual", style = MaterialTheme.typography.headlineSmall)
        Row {
            OutlinedTextField(value = titulo, onValueChange = { titulo = it }, label = { Text("Gasto") }, modifier = Modifier.weight(1f))
            OutlinedTextField(value = valor, onValueChange = { valor = it }, label = { Text("$") }, modifier = Modifier.width(100.dp))
            IconButton(onClick = {
                if(titulo.isNotBlank() && valor.isNotBlank()) {
                    listaTemp.add(GastoFijo(titulo = titulo, valor = valor.toDoubleOrNull() ?: 0.0, periodoAsignado = periodoActual))
                    titulo = ""; valor = ""
                }
            }) { Icon(Icons.Default.Add, "Añadir") }
        }
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(listaTemp.filter { it.periodoAsignado == periodoActual }) { Text("${it.titulo}: $${it.valor}", modifier = Modifier.padding(8.dp)) }
        }
        Button(onClick = { if(periodoActual < totalPeriodos) periodoActual++ else onFinalizar(listaTemp) }, modifier = Modifier.fillMaxWidth()) {
            Text(if(periodoActual < totalPeriodos) "Siguiente Lista" else "Finalizar")
        }
    }
}