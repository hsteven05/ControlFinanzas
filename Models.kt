import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "perfil_usuario")
data class PerfilUsuario(
    @PrimaryKey val id: Int = 1,
    val tipoIngreso: String
)

@Entity(tableName = "gastos_fijos")
data class GastoFijo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titulo: String,
    val valor: Double,
    val estaPagado: Boolean = false,
    val periodoAsignado: Int
)

@Entity(tableName = "transacciones_extra")
data class TransaccionExtra(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val concepto: String,
    val monto: Double,
    val esIngreso: Boolean,
    val fecha: Long = System.currentTimeMillis()
)

@Dao
interface FinanzasDao {
    @Query("SELECT * FROM perfil_usuario WHERE id = 1")
    fun obtenerPerfil(): Flow<PerfilUsuario?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun guardarPerfil(perfil: PerfilUsuario)

    @Query("DELETE FROM perfil_usuario")
    suspend fun borrarPerfil()

    @Query("SELECT * FROM gastos_fijos")
    fun obtenerTodosLosGastos(): Flow<List<GastoFijo>>

    @Insert
    suspend fun insertarGasto(gasto: GastoFijo)

    @Update
    suspend fun actualizarGasto(gasto: GastoFijo)

    @Query("DELETE FROM gastos_fijos")
    suspend fun borrarTodosLosGastos()
}