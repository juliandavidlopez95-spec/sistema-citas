import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
// importamos las clases necesarias para manejar la conexión a la base de datos y las operaciones SQL
public class HorarioDAO {
// Método para listar los horarios disponibles
    public ArrayList<Horario> listarHorariosDisponibles() {
// Creamos una lista para almacenar los horarios disponibles
        ArrayList<Horario> lista = new ArrayList<>();
        String sql = "SELECT * FROM horario WHERE Horario_Disponible = 1";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Horario horario = new Horario(
                    rs.getInt("idHORARIO"),
                    rs.getDate("Fecha_Cita"),
                    rs.getTime("Hora_Inicio_Cita"),
                    rs.getTime("Hora_Fin_Cita"),
                    rs.getBoolean("Horario_Disponible")
                );
// Agregamos el horario a la lista
                lista.add(horario);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar horarios disponibles: " + e.getMessage());
        }
// Devolvemos la lista de horarios disponibles
        return lista;
    }
// Método para marcar un horario como no disponible
    public boolean marcarHorarioNoDisponible(int idHorario) {

        
// Actualizamos el campo Horario_Disponible a 0 para el horario especificado
        String sql = "UPDATE horario SET Horario_Disponible = 0 WHERE idHORARIO = ?";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idHorario);
            int filasAfectadas = ps.executeUpdate();

            return filasAfectadas > 0;
// Si se actualizó al menos una fila, devolvemos true

        } catch (SQLException e) {
            System.out.println("Error al actualizar horario: " + e.getMessage());
            return false;
        }
// Si ocurrió un error, devolvemos false
    }
// Método para marcar un horario como disponible
     public boolean marcarHorarioDisponible(int idHorario) {
        String sql = "UPDATE horario SET Horario_Disponible = 1 WHERE idHORARIO = ?";
// Actualizamos el campo Horario_Disponible a 1 para el horario especificado
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idHorario);
            int filasAfectadas = ps.executeUpdate();

            return filasAfectadas > 0;
// Si se actualizó al menos una fila, devolvemos true
        } catch (SQLException e) {
            System.out.println("Error al actualizar horario: " + e.getMessage());
            return false;
        }
// Si ocurrió un error, devolvemos false
    }
}