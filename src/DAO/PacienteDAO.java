import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
// Importamos los modulos y las conexiones. +

public class PacienteDAO {
// Creamos la clase pacienteDAO para poder registrar los pacientes.

    public boolean registrarPaciente(Paciente paciente) {
 // Creamos la consulta SQL para insertar un nuevo paciente en la base de datos.
        String sql = "INSERT INTO PACIENTE (Nombre_paciente, Apellido_paciente, Documento, correo, telefono) VALUES (?, ?, ?, ?, ?)";
        
//Creamos la funcion para insertar pacientes. 
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, paciente.getNombrePaciente());
            ps.setString(2, paciente.getApellidoPaciente());
            ps.setString(3, paciente.getDocumento());
            ps.setString(4, paciente.getCorreo());
            ps.setString(5, paciente.getTelefono());

            ps.executeUpdate();
            return true;
//Cuando hayamos introducido el paciente aqui nos respondera. 
        } catch (SQLException e) {
            System.out.println("Error al registrar paciente: " + e.getMessage());
            return false;
// Si hay algun error el sistema nos lo indicara.
        }
    }
    public Paciente buscarPacientePorDocumento(String documento) {
// Método para buscar un paciente por su documento
        String sql = "SELECT * FROM paciente WHERE Documento = ?";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, documento);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Paciente paciente = new Paciente(
                    rs.getString("Nombre_paciente"),
                    rs.getString("Apellido_paciente"),
                    rs.getString("Documento"),
                    rs.getString("correo"),
                    rs.getString("telefono")
                );
// Si el paciente existe, se crea un objeto Paciente con los datos obtenidos de la base de datos y se devuelve.
                paciente.setIdPaciente(rs.getInt("idPACIENTE"));
                return paciente;
            }
// Si no se encuentra ningún paciente con el documento proporcionado, se devuelve null.
        } catch (SQLException e) {
            System.out.println("Error al buscar paciente: " + e.getMessage());
        }

        return null;
    }
}
