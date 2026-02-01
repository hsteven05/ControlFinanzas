import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val db = AppDatabase.getDatabase(this)
            val vm = remember { FinanzasViewModel(db.finanzasDao()) }
            val perfil by vm.perfil.collectAsState(initial = null)
            val gastos by vm.todosLosGastos.collectAsState(initial = emptyList())

            when {
                perfil == null -> RegistroPerfilScreen { vm.guardarPerfil(it) }
                gastos.isEmpty() -> ConfiguracionGastosScreen(perfil!!.tipoIngreso) { lista ->
                    lista.forEach { vm.agregarGasto(it.titulo, it.valor, it.periodoAsignado) }
                }
                else -> MainDashboardScreen(gastos, { vm.togglePagoGasto(it) }, { vm.resetearTodo() })
            }
        }
    }
}