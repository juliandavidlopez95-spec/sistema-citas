import java.sql.Date;
// Clase Cita que representa las citas médicas programadas
public class Cita {
    private int idCita;
    private int idPaciente;
    private int idHorario;
    private int idEstadoCita;
    private int idTipoCita;
    private Date idFechaCreacion;
// Constructor de la clase Cita
    public Cita(int idPaciente, int idHorario, int idEstadoCita, int idTipoCita, Date idFechaCreacion) {
        this.idPaciente = idPaciente;
        this.idHorario = idHorario;
        this.idEstadoCita = idEstadoCita;
        this.idTipoCita = idTipoCita;
        this.idFechaCreacion = idFechaCreacion;
    }
// Getters y Setters para los atributos de nuestra clase Cita
    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public int getIdEstadoCita() {
        return idEstadoCita;
    }

    public void setIdEstadoCita(int idEstadoCita) {
        this.idEstadoCita = idEstadoCita;
    }

    public int getIdTipoCita() {
        return idTipoCita;
    }

    public void setIdTipoCita(int idTipoCita) {
        this.idTipoCita = idTipoCita;
    }

    public Date getIdFechaCreacion() {
        return idFechaCreacion;
    }

    public void setIdFechaCreacion(Date idFechaCreacion) {
        this.idFechaCreacion = idFechaCreacion;
    }
}
