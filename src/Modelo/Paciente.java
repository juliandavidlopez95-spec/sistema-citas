public class Paciente {
// Atributos de la clase Paciente
    private int idPaciente;
    private String nombrePaciente;
    private String apellidoPaciente;
    private String documento;
    private String correo;
    private String telefono;

// Constructor de la clase Paciente
    public Paciente(String nombrePaciente, String apellidoPaciente, String documento, String correo, String telefono) {
        this.nombrePaciente = nombrePaciente;
        this.apellidoPaciente = apellidoPaciente;
        this.documento = documento;
        this.correo = correo;
        this.telefono = telefono;
    }
// Getters y Setters para los atributos de nuestra clase Paciente
    public int getIdPaciente() {
        return idPaciente;
    }
// Id del paciente que se agrega a la base de datos, se genera automáticamente, por eso no se incluye en el constructor de la clase Paciente y se asigna a través de un setter después de la inserción en la base de datos. 
    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getApellidoPaciente() {
        return apellidoPaciente;
    }

    public void setApellidoPaciente(String apellidoPaciente) {
        this.apellidoPaciente = apellidoPaciente;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
