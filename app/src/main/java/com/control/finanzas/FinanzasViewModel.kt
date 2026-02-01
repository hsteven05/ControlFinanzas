import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FinanzasViewModel(private val dao: FinanzasDao) : ViewModel() {

    val perfil = dao.obtenerPerfil()
    val todosLosGastos = dao.obtenerTodosLosGastos()

    fun guardarPerfil(tipo: String) {
        viewModelScope.launch { dao.guardarPerfil(PerfilUsuario(tipoIngreso = tipo)) }
    }

    fun agregarGasto(titulo: String, valor: Double, periodo: Int) {
        viewModelScope.launch { dao.insertarGasto(GastoFijo(titulo = titulo, valor = valor, periodoAsignado = periodo)) }
    }

    fun togglePagoGasto(gasto: GastoFijo) {
        viewModelScope.launch { dao.actualizarGasto(gasto.copy(estaPagado = !gasto.estaPagado)) }
    }

    fun resetearTodo() {
        viewModelScope.launch {
            dao.borrarTodosLosGastos()
            dao.borrarPerfil()
        }
    }
}