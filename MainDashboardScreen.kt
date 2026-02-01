import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainDashboardScreen(gastos: List<GastoFijo>, onToggle: (GastoFijo) -> Unit, onReset: () -> Unit) {
    var showMenu by remember { mutableStateOf(false) }
    val pagado = gastos.filter { it.estaPagado }.sumOf { it.valor }
    val total = gastos.sumOf { it.valor }
    val progreso = if(total > 0) (pagado / total).toFloat() else 0f

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Resumen") }, actions = {
                IconButton(onClick = { showMenu = true }) { Icon(Icons.Default.MoreVert, "Menú") }
                DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                    DropdownMenuItem(text = { Text("Borrar todo", color = Color.Red) }, onClick = { onReset(); showMenu = false })
                }
            })
        }
    ) { p ->
        Column(modifier = Modifier.padding(p).padding(16.dp)) {
            LinearProgressIndicator(progress = { progreso }, modifier = Modifier.fillMaxWidth().height(12.dp), color = Color.Green)
            Text("Pagado: $pagado de $total")
            LazyColumn {
                items(gastos) { gasto ->
                    Row {
                        Checkbox(checked = gasto.estaPagado, onCheckedChange = { onToggle(gasto) })
                        Text("${gasto.titulo} ($${gasto.valor})")
                    }
                }
            }
        }
    }
}