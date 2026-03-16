import java.sql.Date;
import java.sql.Time;
// Clase Horario que representa los horarios disponibles para las citas médicas
public class Horario {
// Atributos de la clase Horario
private int idHorario;
private Date fechaCita;
private Time horaInicioCita;
private Time horaFinCita;
private boolean horarioDisponible;
// Constructor de la clase Horario
public Horario(int idHorario, Date fechaCita, Time horaInicioCita, Time horaFinCita, boolean horarioDisponible) {
    this.idHorario = idHorario;
    this.fechaCita = fechaCita;
    this.horaInicioCita = horaInicioCita;
    this.horaFinCita = horaFinCita;
    this.horarioDisponible = horarioDisponible;

}
// Getters y Setters para los atributos de nuestra clase Horario
public int getIdHorario() {
    return idHorario;
}
public void setIdHorario(int idHorario) {
    this.idHorario = idHorario;
}
public Date getFechaCita() {
    return fechaCita;
}
public void setFechaCita(Date fechaCita) {
    this.fechaCita = fechaCita;
}
public Time getHoraInicioCita() {
    return horaInicioCita;
}
public void setHoraInicioCita(Time horaInicioCita) {
    this.horaInicioCita = horaInicioCita;
}
public Time getHoraFinCita() {
    return horaFinCita;
}       
public void setHoraFinCita(Time horaFinCita) {
    this.horaFinCita = horaFinCita;
}
public boolean isHorarioDisponible() {
    return horarioDisponible;
}
public void setHorarioDisponible(boolean horarioDisponible) {
    this.horarioDisponible = horarioDisponible;
}

}