import React, { useState } from "react";

function Citas() {
  const [pacienteActivo] = useState({
    idPaciente: 1,
    nombre: "Julian David Lopez",
    documento: "1234567",
  });

  const horariosBase = [
    "2026-04-06 | 08:00 AM - 09:00 AM",
    "2026-04-06 | 09:00 AM - 10:00 AM",
    "2026-04-06 | 10:00 AM - 11:00 AM",
    "2026-04-07 | 02:00 PM - 03:00 PM",
    "2026-04-07 | 03:00 PM - 04:00 PM",
  ];

  const [horariosDisponibles, setHorariosDisponibles] = useState(horariosBase);
  const [horarioSeleccionado, setHorarioSeleccionado] = useState("");
  const [mensaje, setMensaje] = useState("");

  const [citas, setCitas] = useState([
    {
      idCita: 1,
      idPaciente: 1,
      paciente: "Julian David Lopez",
      documento: "1234567",
      horario: "2026-04-01 | 08:00 AM - 09:00 AM",
      horarioAnterior: "",
      estado: "Cancelada",
    },
    {
      idCita: 2,
      idPaciente: 1,
      paciente: "Julian David Lopez",
      documento: "1234567",
      horario: "2026-04-02 | 09:00 AM - 10:00 AM",
      horarioAnterior: "2026-04-01 | 08:00 AM - 09:00 AM",
      estado: "Reagendada",
    },
  ]);

  const [reagendandoId, setReagendandoId] = useState(null);
  const [nuevoHorario, setNuevoHorario] = useState("");

  const programarCita = (e) => {
    e.preventDefault();

    if (!horarioSeleccionado) {
      setMensaje("Debes seleccionar un horario disponible.");
      return;
    }

    const nuevaCita = {
      idCita: citas.length + 1,
      idPaciente: pacienteActivo.idPaciente,
      paciente: pacienteActivo.nombre,
      documento: pacienteActivo.documento,
      horario: horarioSeleccionado,
      horarioAnterior: "",
      estado: "Programada",
    };

    setCitas([...citas, nuevaCita]);
    setHorariosDisponibles(
      horariosDisponibles.filter((horario) => horario !== horarioSeleccionado)
    );
    setHorarioSeleccionado("");
    setMensaje(`Tu cita fue programada correctamente para ${horarioSeleccionado}.`);
  };

  const cancelarCita = (idCita) => {
    const citaSeleccionada = citas.find((cita) => cita.idCita === idCita);

    if (!citaSeleccionada) {
      setMensaje("No se encontró la cita.");
      return;
    }

    const citasActualizadas = citas.map((cita) =>
      cita.idCita === idCita ? { ...cita, estado: "Cancelada" } : cita
    );

    setCitas(citasActualizadas);

    if (!horariosDisponibles.includes(citaSeleccionada.horario)) {
      setHorariosDisponibles([...horariosDisponibles, citaSeleccionada.horario]);
    }

    setMensaje(`Tu cita en ${citaSeleccionada.horario} fue cancelada correctamente.`);
  };

  const iniciarReagendamiento = (idCita) => {
    setReagendandoId(idCita);
    setNuevoHorario("");
    setMensaje("");
  };

  const confirmarReagendamiento = (idCita) => {
    const citaSeleccionada = citas.find((cita) => cita.idCita === idCita);

    if (!citaSeleccionada) {
      setMensaje("No se encontró la cita.");
      return;
    }

    if (!nuevoHorario) {
      setMensaje("Debes seleccionar un nuevo horario.");
      return;
    }

    if (!horariosDisponibles.includes(nuevoHorario)) {
      setMensaje("El horario seleccionado no está disponible.");
      return;
    }

    const horarioAnterior = citaSeleccionada.horario;

    const citasActualizadas = citas.map((cita) =>
      cita.idCita === idCita
        ? {
            ...cita,
            horario: nuevoHorario,
            horarioAnterior: horarioAnterior,
            estado: "Reagendada",
          }
        : cita
    );

    setCitas(citasActualizadas);

    setHorariosDisponibles([
      ...horariosDisponibles.filter((horario) => horario !== nuevoHorario),
      horarioAnterior,
    ]);

    setReagendandoId(null);
    setNuevoHorario("");
    setMensaje(
      `Tu cita ha sido reagendada al horario ${nuevoHorario}. Antes estaba programada en ${horarioAnterior}.`
    );
  };

  const citasActivas = citas.filter(
    (cita) =>
      (cita.estado === "Programada" || cita.estado === "Reagendada") &&
      cita.idPaciente === pacienteActivo.idPaciente
  );

  const citasAnteriores = citas.filter(
    (cita) =>
      cita.estado === "Cancelada" && cita.idPaciente === pacienteActivo.idPaciente
  );

  const citasAnterioresRecientes = citasAnteriores.slice(-5).reverse();

  return (
    <div className="modulo-container">
      <h1>Módulo de Citas</h1>
      <p>
        En esta sección el paciente activo puede programar, cancelar y reagendar
        citas utilizando los horarios disponibles del sistema.
      </p>

      <div className="tarjeta-info">
        <h2>Paciente activo</h2>
        <p><strong>Nombre:</strong> {pacienteActivo.nombre}</p>
        <p><strong>Documento:</strong> {pacienteActivo.documento}</p>
      </div>

      {mensaje && <p className="mensaje-confirmacion">{mensaje}</p>}

      <div className="tabla-container">
        <h2>CITAS ANTERIORES</h2>
        <p>Solo se mostrarán las últimas 5 citas canceladas.</p>

        {citasAnterioresRecientes.length === 0 ? (
          <p>No hay citas anteriores registradas.</p>
        ) : (
          <table className="tabla">
            <thead>
              <tr>
                <th>ID</th>
                <th>Horario</th>
                <th>Estado</th>
                <th>Detalle</th>
              </tr>
            </thead>
            <tbody>
              {citasAnterioresRecientes.map((cita) => (
                <tr key={cita.idCita}>
                  <td>{cita.idCita}</td>
                  <td>{cita.horario}</td>
                  <td>{cita.estado}</td>
                  <td>Tu cita programada para {cita.horario} fue cancelada.</td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>

      <div className="tabla-container">
        <h2>NUEVA CITA</h2>

        <form className="formulario" onSubmit={programarCita}>
          <div className="campo">
            <label>Horario disponible</label>
            <select
              value={horarioSeleccionado}
              onChange={(e) => setHorarioSeleccionado(e.target.value)}
            >
              <option value="">Seleccione un horario</option>
              {horariosDisponibles.map((horario, index) => (
                <option key={index} value={horario}>
                  {horario}
                </option>
              ))}
            </select>
          </div>

          <button type="submit" className="btn-principal">
            Programar cita
          </button>
        </form>

        <h2 style={{ marginTop: "30px" }}>CITAS ACTIVAS</h2>
        <p>Aquí se muestran tus citas programadas y reagendadas actualmente activas.</p>

        {citasActivas.length === 0 ? (
          <p>No hay citas activas registradas para este paciente.</p>
        ) : (
          <table className="tabla">
            <thead>
              <tr>
                <th>ID</th>
                <th>Horario actual</th>
                <th>Estado</th>
                <th>Detalle</th>
                <th>Acciones</th>
              </tr>
            </thead>
            <tbody>
              {citasActivas.map((cita) => (
                <tr key={cita.idCita}>
                  <td>{cita.idCita}</td>
                  <td>{cita.horario}</td>
                  <td>{cita.estado}</td>
                  <td>
                    {cita.estado === "Reagendada" && cita.horarioAnterior ? (
                      <>
                        Tu cita ha sido reagendada al horario <strong>{cita.horario}</strong>.
                        <br />
                        Antes estaba programada en <strong>{cita.horarioAnterior}</strong>.
                      </>
                    ) : (
                      <>Tu cita está programada para el horario <strong>{cita.horario}</strong>.</>
                    )}
                  </td>
                  <td>
                    <button
                      type="button"
                      onClick={() => cancelarCita(cita.idCita)}
                    >
                      Cancelar
                    </button>

                    {reagendandoId === cita.idCita ? (
                      <div style={{ marginTop: "10px" }}>
                        <select
                          value={nuevoHorario}
                          onChange={(e) => setNuevoHorario(e.target.value)}
                        >
                          <option value="">Seleccione nuevo horario</option>
                          {horariosDisponibles.map((horario, index) => (
                            <option key={index} value={horario}>
                              {horario}
                            </option>
                          ))}
                        </select>

                        <button
                          type="button"
                          onClick={() => confirmarReagendamiento(cita.idCita)}
                          style={{ marginLeft: "8px" }}
                        >
                          Confirmar
                        </button>
                      </div>
                    ) : (
                      <button
                        type="button"
                        onClick={() => iniciarReagendamiento(cita.idCita)}
                      >
                        Reagendar
                      </button>
                    )}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </div>
  );
}

export default Citas;