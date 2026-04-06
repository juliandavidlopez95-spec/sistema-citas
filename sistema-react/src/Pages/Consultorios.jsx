import React, { useState } from "react";

function Consultorios() {
  const [centros] = useState([
    {
      id: 1,
      ciudad: "Cali",
      nombre: "Fundación Valle del Lili",
      direccion: "Carrera 98 # 18-49, Cali, Valle del Cauca",
      atencion: "Consulta externa, medicina general, especialidades y apoyo diagnóstico",
      horario: "Lunes a sábado, 7:00 AM - 7:00 PM",
      estado: "Centro principal de referencia",
    },
    {
      id: 2,
      ciudad: "Bogotá",
      nombre: "Fundación Santa Fe de Bogotá",
      direccion: "Carrera 7 # 117-15, Bogotá D.C.",
      atencion: "Atención integral, consulta prioritaria y seguimiento médico programado",
      horario: "Lunes a sábado, 6:30 AM - 7:00 PM",
      estado: "Centro principal de referencia",
    },
    {
      id: 3,
      ciudad: "Medellín",
      nombre: "Hospital General de Medellín",
      direccion: "Calle 1 # 48-35, Medellín, Antioquia",
      atencion: "Consulta general, apoyo clínico y atención programada por red",
      horario: "Lunes a sábado, 7:00 AM - 6:00 PM",
      estado: "Centro principal de referencia",
    },
  ]);

  return (
    <div className="modulo-container">
      <h1>Módulo de Consultorios</h1>
      <p>
        En esta sección se presenta la red principal de centros médicos
        disponibles para la atención de los pacientes del sistema.
      </p>

      <div className="tarjeta-info">
        <h2>Información importante</h2>
        <p>
          Toda cita será asignada a nuestro centro médico principal de atención,
          el cual cuenta con equipamiento y profesionales capacitados para
          atender tus prioridades médicas.
        </p>
        <p>
          Nuestros centros médicos principales se encuentran en Cali, Bogotá y
          Medellín. La asignación final del punto de atención dependerá de la
          disponibilidad de agenda y de la red de soporte vigente para tu cita.
        </p>
      </div>

      <div className="tabla-container">
        <h2>Centros de atención disponibles</h2>
        <p>
          A continuación se muestran los centros principales de nuestra red
          médica de referencia.
        </p>

        <table className="tabla">
          <thead>
            <tr>
              <th>ID</th>
              <th>Ciudad</th>
              <th>Centro médico</th>
              <th>Dirección</th>
              <th>Servicios principales</th>
              <th>Horario</th>
            </tr>
          </thead>
          <tbody>
            {centros.map((centro) => (
              <tr key={centro.id}>
                <td>{centro.id}</td>
                <td>{centro.ciudad}</td>
                <td>{centro.nombre}</td>
                <td>{centro.direccion}</td>
                <td>{centro.atencion}</td>
                <td>{centro.horario}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default Consultorios;