import React, { useState } from "react";

function Medicos() {
  const [medicos] = useState([
    { id: 1. , nombre: "Dra. Laura Gómez", especialidad: "Medicina General", ciudad: "Cali", disponibilidad: "Disponible" },
    { id: 2. , nombre: "Dr. Andrés Salazar", especialidad: "Pediatría", ciudad: "Cali", disponibilidad: "Disponible" },
    { id: 3. , nombre: "Dra. Paula Herrera", especialidad: "Dermatología", ciudad: "Cali", disponibilidad: "Alta demanda" },
    { id: 4. , nombre: "Dr. Camilo Rojas", especialidad: "Cardiología", ciudad: "Cali", disponibilidad: "Sujeto a confirmación" },
    { id: 5. , nombre: "Dra. Natalia Muñoz", especialidad: "Ginecología", ciudad: "Cali", disponibilidad: "Disponible" },
    { id: 6. , nombre: "Dr. Felipe Castro", especialidad: "Medicina Interna", ciudad: "Medellín", disponibilidad: "Disponible" },
    { id: 7. , nombre: "Dra. Juliana Restrepo", especialidad: "Pediatría", ciudad: "Medellín", disponibilidad: "Alta demanda" },
    { id: 8. , nombre: "Dr. Sebastián Vélez", especialidad: "Ortopedia", ciudad: "Medellín", disponibilidad: "Disponible" },
    { id: 9. , nombre: "Dra. Mariana Jaramillo", especialidad: "Psicología Clínica", ciudad: "Medellín", disponibilidad: "Disponible" },
    { id: 10. , nombre: "Dr. Nicolás Arango", especialidad: "Neurología", ciudad: "Medellín", disponibilidad: "Sujeto a confirmación" },
    { id: 11. , nombre: "Dra. Valentina Torres", especialidad: "Medicina General", ciudad: "Bogotá", disponibilidad: "Disponible" },
    { id: 12. , nombre: "Dr. Juan Esteban Rincón", especialidad: "Cardiología", ciudad: "Bogotá", disponibilidad: "Alta demanda" },
    { id: 13. , nombre: "Dra. Catalina Prieto", especialidad: "Dermatología", ciudad: "Bogotá", disponibilidad: "Disponible" },
    { id: 14. , nombre: "Dr. Mateo Cárdenas", especialidad: "Ginecología", ciudad: "Bogotá", disponibilidad: "Sujeto a confirmación" },
    { id: 15. , nombre: "Dra. Gabriela León", especialidad: "Pediatría", ciudad: "Bogotá", disponibilidad: "Disponible" },
    { id: 16. , nombre: "Dr. Daniel Quintero", especialidad: "Otorrinolaringología", ciudad: "Cali", disponibilidad: "Disponible" },
    { id: 17. , nombre: "Dra. Sara Pineda", especialidad: "Medicina Familiar", ciudad: "Medellín", disponibilidad: "Disponible" },
    { id: 18. , nombre: "Dr. Esteban Molina", especialidad: "Urología", ciudad: "Bogotá", disponibilidad: "Alta demanda" },
    { id: 19. , nombre: "Dra. Lina Acevedo", especialidad: "Endocrinología", ciudad: "Cali", disponibilidad: "Sujeto a confirmación" },
    { id: 20. , nombre: "Dr. Miguel Buitrago", especialidad: "Psiquiatría", ciudad: "Bogotá", disponibilidad: "Disponible" },
  ]);

  return (
    <div className="modulo-container">
      <h1>Módulo de Médicos</h1>
      <p>
        En esta sección se presenta la red de profesionales disponible dentro del
        sistema de gestión de citas médicas.
      </p>

      <div className="tarjeta-info">
        <h2>Información importante</h2>
        <p>
          Una vez agendada tu cita, se te confirmará vía correo electrónico el
          profesional asignado dentro de las próximas 24 horas.
        </p>
        <p>
          En caso de ajustes de agenda o disponibilidad, la cita podrá ser
          reprogramada a la fecha disponible más cercana.
        </p>
      </div>

      <div className="tabla-container">
        <h2>Doctores en nuestra red</h2>
        <p>
          A continuación se muestra una lista referencial de profesionales
          disponibles en las principales ciudades de atención.
        </p>

        <table className="tabla">
          <thead>
            <tr>
              <th>ID</th>
              <th>Nombre</th>
              <th>Especialidad</th>
              <th>Ciudad</th>
            </tr>
          </thead>
          <tbody>
            {medicos.map((medico) => (
              <tr key={medico.id}>
                <td>{medico.id}</td>
                <td>{medico.nombre}</td>
                <td>{medico.especialidad}</td>
                <td>{medico.ciudad}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default Medicos;