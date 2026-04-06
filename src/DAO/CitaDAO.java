import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
// Importar la clase de conexión a la base de datos

public class CitaDAO {
// Creamos la clase CitaDAO para manejar las operaciones 

// Método para programar una nueva cita
    public boolean programarCita(Cita cita) {
        String sql = "INSERT INTO cita (idPaciente, id_Horario, id_Estado_Cita, id_Tipo_Cita, id_Fecha_Creacion) VALUES (?, ?, ?, ?, ?)";
// Creamos un string para la consulta SQL que insertará una nueva cita en la base de datos. Utilizamos parámetros (?) para evitar inyecciones SQL y facilitar la inserción de datos.
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

// Establecemos los valores de nuestros parametros que corresponde a los atributos de la clase Cita
            pstmt.setInt(1, cita.getIdPaciente());
            pstmt.setInt(2, cita.getIdHorario());
            pstmt.setInt(3, cita.getIdEstadoCita());
            pstmt.setInt(4, cita.getIdTipoCita());
            pstmt.setDate(5, cita.getIdFechaCreacion());
            

            int filas = pstmt.executeUpdate();

            // Si la inserción fue exitosa, marcamos el horario como no disponible
            if (filas > 0) {
                HorarioDAO horarioDAO = new HorarioDAO();
                horarioDAO.marcarHorarioNoDisponible(cita.getIdHorario());
                return true;
            }
            // Si no se insertó ninguna fila, retornamos false
            return false;

        } catch (SQLException e) {
            System.out.println("Error al programar la cita: " + e.getMessage());
            return false;
        }
    }
// Método para listar las citas programadas de un paciente
    public ArrayList<Cita> listarCitasProgramadasPorPaciente(int idPaciente) {
        ArrayList<Cita> lista = new ArrayList<>();
        String sql = "SELECT * FROM cita WHERE idPaciente = ? AND id_Estado_Cita = 1";
    
// Creamos un string para la consulta SQL que seleccionará todas las citas programadas
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

// Establecemos el valor del parámetro para el ID del paciente
            pstmt.setInt(1, idPaciente);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Cita cita = new Cita(
                    rs.getInt("idPaciente"),
                    rs.getInt("id_Horario"),
                    rs.getInt("id_Estado_Cita"),
                    rs.getInt("id_Tipo_Cita"),
                    rs.getDate("id_Fecha_Creacion")
                );
// Creamos un nuevo objeto Cita utilizando los datos del ResultSet y lo agregaremos a la lista de citas programadas.
                cita.setIdCita(rs.getInt("idCITA"));
                lista.add(cita);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar citas: " + e.getMessage());
        }
// Retornamos la lista de citas programadas para el paciente.
        return lista;
    }
// Método para cancelar una cita programada
    public boolean cancelarCita(int idCita, int idHorario) {
        String sql = "UPDATE cita SET id_Estado_Cita = 2 WHERE idCITA = ?";
// Creamos uns string para la consulta SQL que actualizará el estado de nuestra cita a cancelada
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idCita);

            int filas = pstmt.executeUpdate();
// Si la actualización fue exitosa, marcamos el horario como disponible nuevamente
            if (filas > 0) {
            HorarioDAO horarioDAO = new HorarioDAO();
            horarioDAO.marcarHorarioDisponible(idHorario);
            return true;
            }
// Si no se actualizó ninguna fila, retornamos false
            return false;
        } catch (SQLException e) {
            System.out.println("Error al cancelar la cita: " + e.getMessage());
            return false;
        }
    }
// Método para reagendar una cita programada
    public boolean reagendarCita(int idCita, int idHorarioAnterior, int idNuevoHorario) {
    String sql = "UPDATE cita SET id_Horario = ?, id_Estado_Cita = 3 WHERE idCITA = ?";
// Creamos un string para la consulta SQL que actualizará el horario de la cita y cambiará su estado a reagendada
    try (Connection conn = ConexionDB.conectar();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, idNuevoHorario);
        pstmt.setInt(2, idCita);

        int filas = pstmt.executeUpdate();
// Si la cita fue actualizada marcaremos el estado del horario anterior como disponible y el nuevo horario como no disponible
        if (filas > 0) {
            HorarioDAO horarioDAO = new HorarioDAO();
            horarioDAO.marcarHorarioDisponible(idHorarioAnterior);
            horarioDAO.marcarHorarioNoDisponible(idNuevoHorario);
            return true;
// Si no se actualizó ninguna fila, retornamos false
        }
        return false;

         } catch (SQLException e) {
        System.out.println("Error al reagendar la cita: " + e.getMessage());
        return false;
         }
// En caso de error, imprimimos el mensaje de error y retornamos false
    }
// Método para listar el historial de citas de un paciente
    public ArrayList<Cita> listarHistorialCitasPorPaciente(int idPaciente) {
        ArrayList<Cita> lista = new ArrayList<>();
        String sql = "SELECT * FROM cita WHERE idPaciente = ? AND id_Estado_Cita IN (1,2,3)";
// Creamos un string para la consulta SQL que seleccionará todas las citas del paciente, independientemente de su estado (programada, cancelada o reagendada).
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idPaciente);
            ResultSet rs = pstmt.executeQuery();
// Iteramos sobre el ResultSet para crear objetos Cita y agregarlos a la lista de historial de citas.
            while (rs.next()) {
                Cita cita = new Cita(
                    rs.getInt("idPaciente"),
                    rs.getInt("id_Horario"),
                    rs.getInt("id_Estado_Cita"),
                    rs.getInt("id_Tipo_Cita"),
                    rs.getDate("id_Fecha_Creacion")
                );
// Creamos un nuevo objeto Cita utilizando los datos del ResultSet y lo agregaremos a la lista de historial de citas.
                cita.setIdCita(rs.getInt("idCITA"));
                lista.add(cita);
            }
// Retornamos la lista de historial de citas para el paciente.
        } catch (SQLException e) {
            System.out.println("Error al listar historial de citas: " + e.getMessage());
        }
// si ocurre un error, imprimimos el mensaje de error.
        return lista;
    }
}
         
