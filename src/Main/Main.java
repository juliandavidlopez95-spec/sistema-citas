import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

import Util.Login;
// Importar las clases de los modelos y DAOs

// Clase principal del sistema de citas que contiene el menú de opciones para el usuario.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
// Bucle principal del programa, nos muestra el menu de opciones y ejecuta la función correspondiente según la elección del usuario.
        do {
            System.out.println("\n=== SISTEMA DE CITAS ===");
            System.out.println("1. Registrar paciente");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Programar cita");
            System.out.println("4. Cancelar cita");
            System.out.println("5. Reagendar cita");
            System.out.println("6. Historial de citas");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    registrarPaciente(sc);
                    break;
                case 2:
                    iniciarSesion(sc);
                    break;
                case 3:
                    programarCita(sc);
                    break;
                case 4:
                    cancelarCita(sc);
                    break;
                case 5:
                    reagendarCita(sc);
                    break;
                case 6:
                    historialCitas(sc);
                    break;
                case 7:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 7);

        sc.close();
    }
// Funcion para reagendar una Cita. 
    private static void reagendarCita(Scanner sc) {
    System.out.println("\n=== REAGENDAR CITA ===");

    System.out.print("Documento del paciente: ");
    String documento = sc.nextLine();

    PacienteDAO pacienteDAO = new PacienteDAO();
    Paciente paciente = pacienteDAO.buscarPacientePorDocumento(documento);
// Verificamos la existencia del paciente antes de continuar. 

    if (paciente == null) {
        System.out.println("No se encontró un paciente con ese documento.");
        return;
    }
//agregamos condicionales para mostrar el estado de la cita y luego solicitamos al usuario que confirme su intención de reagendar la cita.
    CitaDAO citaDAO = new CitaDAO();
    ArrayList<Cita> citas = citaDAO.listarCitasProgramadasPorPaciente(paciente.getIdPaciente());
// Si no hay citas programadas, muestra el mensaje y finaliza. 
    if (citas.isEmpty()) {
        System.out.println("No hay citas programadas para este paciente.");
        return;
    }
// Su si hay citas programadas, muestra el mensaje con el estado de cada cita y luego solicita al usuario que seleccione la cita que desea reagendar.
    System.out.println("\nCitas programadas:");
    for (Cita c : citas) {
        String estadoTexto;

        if (c.getIdEstadoCita() == 1) {
            estadoTexto = "Programada";
        } else if (c.getIdEstadoCita() == 2) {
            estadoTexto = "Cancelada";
        } else if (c.getIdEstadoCita() == 3) {
            estadoTexto = "Reagendada";
        } else {
            estadoTexto = "Estado desconocido";
        }

        System.out.println(
            "Cita #" + c.getIdCita() +
            " | Fecha: " + c.getIdFechaCreacion() +
            " | Estado: " + estadoTexto
        );
    }
 // Solicita al usuario que seleccione la cita que desea reagendar.
    System.out.print("\nSeleccione el numero de la cita que desea reagendar: ");
    int idCita = sc.nextInt();
    sc.nextLine();
    Cita citaSeleccionada = null;

    for (Cita c : citas) {
        if (c.getIdCita() == idCita) {
            citaSeleccionada = c;
            break;
        }
    }
//Si no se encuentra la cita seleccionada, muestra el mensaje y finaliza.
    if (citaSeleccionada == null) {
        System.out.println("No se encontró una cita con ese número.");
        return;
    }
// Si se encuentra la cita seleccionada, solicita al usuario que confirme su intención de reagendar la cita.
    System.out.print("¿Estas seguro que deseas reagendar la cita #" + citaSeleccionada.getIdCita() + "? (S/N): ");
    String confirmacion = sc.nextLine();
// Si el usuario confirma que desea reagendar la cita, se procede a mostrar los horarios disponibles para que el usuario seleccione uno nuevo. Si el usuario cancela, muestra el mensaje y finaliza.
    if (!confirmacion.equalsIgnoreCase("S")) {
        System.out.println("Reagendamiento cancelado por el usuario.");
        return;
    }

    int idHorarioAnterior = citaSeleccionada.getIdHorario();

    HorarioDAO horarioDAO = new HorarioDAO();
    ArrayList<Horario> horarios = horarioDAO.listarHorariosDisponibles();
// Si no hay horarios disponibles, muestra el mensaje y finaliza.
    if (horarios.isEmpty()) {
        System.out.println("No hay horarios disponibles para reagendar.");
        return;
    }
// Si hay horarios disponibles, muestra el mensaje con los horarios disponibles y luego solicita al usuario que seleccione el nuevo horario deseado.
    System.out.println("\nHorarios disponibles:");
    for (Horario h : horarios) {
        System.out.println(
            h.getIdHorario() + " | " +
            h.getFechaCita() + " | " +
            h.getHoraInicioCita() + " - " +
            h.getHoraFinCita()
        );
    }
// Solicita al usuario que seleccione el nuevo horario deseado.
    System.out.print("\nSeleccione el nuevo horario deseado (ID): ");
    int idNuevoHorario = sc.nextInt();
    sc.nextLine();
// Verificamos que el horario seleccionado sea diferente al horario actual de la cita. 
    if (idNuevoHorario == idHorarioAnterior) {
    System.out.println("Debe seleccionar un horario diferente al actual.");
    return;
    }

    Horario horarioSeleccionado = null;

    for (Horario h : horarios) {
    if (h.getIdHorario() == idNuevoHorario) {
        horarioSeleccionado = h;
        break;
        }
    }
// Si no se encuentra el horario seleccionado, muestra el mensaje y finaliza.
    if (horarioSeleccionado == null) {
    System.out.println("El horario seleccionado no está disponible.");
    return;
    }   

    boolean reagendada = citaDAO.reagendarCita(idCita, idHorarioAnterior, idNuevoHorario);
// Si la cita se reagenda correctamente, muestra el mensaje de éxito. Si no se pudo reagendar, muestra el mensaje de error.
    if (reagendada) {
    System.out.println("Cita reagendada correctamente.");
    } else {
    System.out.println("No se pudo reagendar la cita.");
    }
    }
// Funcion para registrar un nuevo paciente en el sistema. 
    public static void registrarPaciente(Scanner sc) {
// Solicitamos al usuario que ingrese los datos del nuevo paciente y creamos un objeto paciente con los datos.
        System.out.println("\n=== REGISTRO DE PACIENTE ===");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Apellido: ");
        String apellido = sc.nextLine();

        System.out.print("Documento: ");
        String documento = sc.nextLine();

        System.out.print("Correo: ");
        String correo = sc.nextLine();

        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();

        Paciente paciente = new Paciente(nombre, apellido, documento, correo, telefono);
        PacienteDAO pacienteDAO = new PacienteDAO();

        boolean registrado = pacienteDAO.registrarPaciente(paciente);
// Si el paciente se registra correctamente, muestra el mensaje de éxito. Si no se pudo registrar, muestra el mensaje de error.
        if (registrado) {
            System.out.println("Paciente registrado correctamente.");
        } else {
            System.out.println("No se pudo registrar el paciente.");
        }
    }
// Funcion para iniciar sesión en el sistema.
    public static void iniciarSesion(Scanner sc) {
// Solicitamos al usuario que ingrese su usuario y contraseña, creamos un objeto login y verificamos si tiene acceso al sistema.
        System.out.println("\n=== INICIO DE SESIÓN ===");
        System.out.print("Usuario: ");
        String usuario = sc.nextLine();

        System.out.print("Contraseña: ");
        String contrasena = sc.nextLine();

    Login login = new Login();
    boolean acceso = login.iniciarSesion(usuario, contrasena);
// Si el usuario tiene acceso, muestra el mensaje de inicio de sesión exitoso. Si no tiene acceso, muestra el mensaje de error.
        if (acceso) {
            System.out.println("Inicio de sesión exitoso.");
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
        }
    }
// Funcion para programar una nueva cita para un paciente existente en el sistema.
    public static void programarCita(Scanner sc) {
// Solicitamos al usuario que ingrese el documento del paciente para poder buscarlo en el sistema. 
    System.out.println("\n=== PROGRAMAR CITA ===");

    System.out.print("Documento del paciente: ");
    String documento = sc.nextLine();

    PacienteDAO pacienteDAO = new PacienteDAO();
    Paciente paciente = pacienteDAO.buscarPacientePorDocumento(documento);
// Si el paciente no existe el sistema muestra el mensaje de error. 
    if (paciente == null) {
        System.out.println("No se encontró un paciente con ese documento.");
        return;
    }
// Si el paciente existe, el sistema muestra el nombre y horarios disponibles para que el usuario pueda seleccionar. 
    System.out.println("Paciente encontrado: " + paciente.getNombrePaciente() + " " + paciente.getApellidoPaciente());

    HorarioDAO horarioDAO = new HorarioDAO();
    ArrayList<Horario> horarios = horarioDAO.listarHorariosDisponibles();

    if (horarios.isEmpty()) {
        System.out.println("No hay horarios disponibles.");
        return;
    }

    System.out.println("\nHorarios disponibles:");
    for (Horario h : horarios) {
        String disponibilidadTexto = h.isHorarioDisponible() ? "Disponible" : "No disponible";

        System.out.println(
            "Horario #" + h.getIdHorario() +
            " | Fecha: " + h.getFechaCita() +
            " | Hora: " + h.getHoraInicioCita() + " - " + h.getHoraFinCita() +
            " | Estado: " + disponibilidadTexto
        );
    }
// Solicita al usuario que seleccione el horario deseado para programar la cita.
    System.out.print("\nSeleccione el numero del horario deseado: ");
    int idHorario = sc.nextInt();
    sc.nextLine();

    Horario horarioSeleccionado = null;

    for (Horario h : horarios) {
        if (h.getIdHorario() == idHorario) {
            horarioSeleccionado = h;
            break;
        }
    }
// Si no se encuentra el horario seleccionado, muestra el mensaje de error.
    if (horarioSeleccionado == null) {
        System.out.println("No se encontró un horario con ese número.");
        return;
    }
// Si se encuentra el horario seleccionado, solicita al usuario que confirme su intención de programar la cita en ese horario.
    System.out.print("¿Estas seguro que deseas programar la cita en el horario #" 
            + horarioSeleccionado.getIdHorario() + "? (S/N): ");
    String confirmacion = sc.nextLine();
// Si el usuario cancela el proceso muestra el mensaje y finaliza. 
    if (!confirmacion.equalsIgnoreCase("S")) {
        System.out.println("Programación cancelada por el usuario.");
        return;
    }
// Si confirma se crea un objeto cita con los datos del paciente. Luego se utiliza el DAO de citas para programar la cita en el sistema.
    int idEstadoCita = 1; // Programada
    int idTipoCita = 1;   // Tipo por defecto
    Date fechaCreacion = new Date(System.currentTimeMillis());

    Cita cita = new Cita(
        paciente.getIdPaciente(),
        idHorario,
        idEstadoCita,
        idTipoCita,
        fechaCreacion
    );

    CitaDAO citaDAO = new CitaDAO();
    boolean registrada = citaDAO.programarCita(cita);
// Si la cita se programa correctamente, muestra el mensaje de éxito. Si no se pudo programar, muestra el mensaje de error.
    if (registrada) {
        System.out.println("Cita programada correctamente.");
    } else {
        System.out.println("No se pudo programar la cita.");
    }
}
// Funcion para cancelar una cita programada para un paciente existente en el sistema.
    public static void cancelarCita(Scanner sc) {
// Solicitamos al usuario que ingrese el documento del paciente para poder buscarlo en el sistema.
    System.out.println("\n=== CANCELAR CITA ===");
    System.out.print("Documento del paciente: ");
    String documento = sc.nextLine();

    PacienteDAO pacienteDAO = new PacienteDAO();
    Paciente paciente = pacienteDAO.buscarPacientePorDocumento(documento);
// Si el paciente no existe el sistema muestra el mensaje de error.
    if (paciente == null) {
        System.out.println("No se encontró un paciente con ese documento.");
        return;
    }
// Si el paciente existe, el sistema muestra las citas programadas para ese paciente.
    CitaDAO citaDAO = new CitaDAO();
    ArrayList<Cita> citas = citaDAO.listarCitasProgramadasPorPaciente(paciente.getIdPaciente());
// Si no hay citas programadas, muestra el mensaje y finaliza.
    if (citas.isEmpty()) {
        System.out.println("No hay citas programadas para este paciente.");
        return;
    }
// Si hay citas programadas muestra el estado.
    System.out.println("\nCitas programadas:");
    for (Cita c : citas) {
        String estadoTexto;

        if (c.getIdEstadoCita() == 1) {
            estadoTexto = "Programada";
        } else if (c.getIdEstadoCita() == 2) {
            estadoTexto = "Cancelada";
        } else if (c.getIdEstadoCita() == 3) {
            estadoTexto = "Reagendada";
        } else {
            estadoTexto = "Estado desconocido";
        }
        System.out.println(
            "Cita #" + c.getIdCita() +
            " | Fecha: " + c.getIdFechaCreacion() +
            " | Estado: " + estadoTexto
        );
    }
// Solicita al usuario que seleccione la cita que desea cancelar.
    System.out.print("\nSeleccione la cita a cancelar (ID): ");
    int idCita = sc.nextInt();
    sc.nextLine();

    Cita citaSeleccionada = null;
// Verificamos que la cita seleccionada exista en la lista de citas programadas para el paciente.
    for (Cita c : citas) {
        if (c.getIdCita() == idCita) {
            citaSeleccionada = c;
            break;
        }
    }
// Si no se encuentra la cita seleccionada, muestra el mensaje de error.
    if (citaSeleccionada == null) {
        System.out.println("No se encontró una cita con ese número.");
        return;
    }
// Si se encuentra la cita, solicitamos al usuario que confirme su intención de cancelar la cita.
    System.out.print("¿Estas seguro que deseas cancelar la cita #" + citaSeleccionada.getIdCita() + "? (S/N): ");
    String confirmacion = sc.nextLine();
// Si el usuario cancela el proceso muestra el mensaje y finaliza.
    if (!confirmacion.equalsIgnoreCase("S")) {
        System.out.println("Cancelación anulada por el usuario.");
        return;
    }
// Si confirma se utiliza el DAO de citas para cancelar la cita en el sistema.
    int idHorario = citaSeleccionada.getIdHorario();

    boolean cancelada = citaDAO.cancelarCita(idCita, idHorario);
// Si la cita se cancela correctamente, muestra el mensaje de éxito. Si no se pudo cancelar, muestra el mensaje de error.
    if (cancelada) {
        System.out.println("Cita cancelada correctamente.");
    } else {
        System.out.println("No se pudo cancelar la cita.");
        }
    }
// Funcion para mostrar el historial de citas de un paciente existente en el sistema.
    public static void historialCitas(Scanner sc) {
// Solicitamos al usuario que ingrese el documento del paciente para poder buscarlo en el sistema.
    System.out.println("\n=== HISTORIAL DE CITAS ===");
    System.out.print("Documento del paciente: ");
    String documento = sc.nextLine();

    PacienteDAO pacienteDAO = new PacienteDAO();
    Paciente paciente = pacienteDAO.buscarPacientePorDocumento(documento);
// Si el paciente no existe el sistema muestra el mensaje de error.
    if (paciente == null) {
        System.out.println("No se encontró un paciente con ese documento.");
        return;
    }
// Si el paciente existe, el sistema muestra el nombre del paciente y luego muestra el historial de citas para ese paciente.
    System.out.println("Paciente encontrado: " + paciente.getNombrePaciente() + " " + paciente.getApellidoPaciente());

    CitaDAO citaDAO = new CitaDAO();
    ArrayList<Cita> citas = citaDAO.listarHistorialCitasPorPaciente(paciente.getIdPaciente());
// Si no hay citas en el historial, muestra el mensaje y finaliza.
    if (citas.isEmpty()) {
        System.out.println("No hay historial de citas para este paciente.");
        return;
    }
// Si hay citas en el historial, muestra el estado de cada cita.
    System.out.println("\nHistorial de citas:");
    for (Cita c : citas) {
        String estadoTexto;

        if (c.getIdEstadoCita() == 1) {
            estadoTexto = "Programada";
        } else if (c.getIdEstadoCita() == 2) {
            estadoTexto = "Cancelada";
        } else if (c.getIdEstadoCita() == 3) {
            estadoTexto = "Reagendada";
        } else {
            estadoTexto = "Estado desconocido";
        }

        System.out.println(
            "Cita #" + c.getIdCita() +
            " | Fecha: " + c.getIdFechaCreacion() +
            " | Estado: " + estadoTexto
        );
    }
}
// Fin de la clase principal del sistema de citas.
}


