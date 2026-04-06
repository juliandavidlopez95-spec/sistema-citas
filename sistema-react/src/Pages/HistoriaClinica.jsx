import React, { useState } from "react";

function HistoriaClinica() {
  const [pacienteActivo] = useState({
    idPaciente: 1,
    nombre: "Julian David Lopez",
    documento: "1234567",
  });

  const [historialCitas] = useState([
    {
      idCita: 1,
      idPaciente: 1,
      horario: "2026-04-01 | 08:00 AM - 09:00 AM",
      estado: "Cancelada",
      horarioAnterior: "",
    },
    {
      idCita: 2,
      idPaciente: 1,
      horario: "2026-04-02 | 09:00 AM - 10:00 AM",
      estado: "Reagendada",
      horarioAnterior: "2026-04-06 | 10:00 AM - 11:00 AM",
    },
    {
      idCita: 3,
      idPaciente: 1,
      horario: "2026-04-07 | 03:00 PM - 04:00 PM",
      estado: "Programada",
      horarioAnterior: "",
    },
  ]);

  const historialPaciente = historialCitas.filter(
    (cita) => cita.idPaciente === pacienteActivo.idPaciente
  );

  const historialReciente = historialPaciente.slice(-5).reverse();

  const generarDetalle = (cita) => {
    if (cita.estado === "Cancelada") {
      return `Tu cita programada para ${cita.horario} fue cancelada.`;
    }

    if (cita.estado === "Reagendada") {
      return `Tu cita fue reagendada al horario ${cita.horario}. Antes estaba programada en ${cita.horarioAnterior}.`;
    }

    return `Tu cita continúa programada para ${cita.horario}.`;
  };

  return (
    <div className="modulo-container">
      <h1>Historial de tus citas</h1>
      <p>
        En esta sección podrás consultar el historial reciente de tus citas
        médicas registradas en el sistema.
      </p>

      <div className="tarjeta-info">
        <h2>Paciente activo</h2>
        <p><strong>Nombre:</strong> {pacienteActivo.nombre}</p>
        <p><strong>Documento:</strong> {pacienteActivo.documento}</p>
      </div>

      <div className="tabla-container">
        <h2>Últimos movimientos</h2>
        <p>Solo se mostrarán las últimas 5 citas registradas para este paciente.</p>

        {historialReciente.length === 0 ? (
          <p>No hay historial de citas disponible.</p>
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
              {historialReciente.map((cita) => (
                <tr key={cita.idCita}>
                  <td>{cita.idCita}</td>
                  <td>{cita.horario}</td>
                  <td>{cita.estado}</td>
                  <td>{generarDetalle(cita)}</td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </div>
  );
}

export default HistoriaClinica;