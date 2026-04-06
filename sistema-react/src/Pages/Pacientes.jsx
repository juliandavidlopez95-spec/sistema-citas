import React, { useState } from "react";

function Pacientes() {
  const [formData, setFormData] = useState({
    nombre: "",
    apellido: "",
    documento: "",
    correo: "",
    telefono: "",
  });

  const [pacientes, setPacientes] = useState([]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (
      !formData.nombre ||
      !formData.apellido ||
      !formData.documento ||
      !formData.correo ||
      !formData.telefono
    ) {
      alert("Por favor, complete todos los campos.");
      return;
    }

    const nuevoPaciente = {
      id: pacientes.length + 1,
      ...formData,
    };

    setPacientes([...pacientes, nuevoPaciente]);

    setFormData({
      nombre: "",
      apellido: "",
      documento: "",
      correo: "",
      telefono: "",
    });
  };

  return (
    <div className="modulo-container">
      <h1>Pacientes</h1>
      <p>En esta sección se realiza el registro y visualización de pacientes.</p>

      <form className="formulario" onSubmit={handleSubmit}>
        <div className="campo">
          <label>Nombre:</label>
          <input
            type="text"
            name="nombre"
            value={formData.nombre}
            onChange={handleChange}
            placeholder="Ingrese el nombre"
          />
        </div>

        <div className="campo">
          <label>Apellido:</label>
          <input
            type="text"
            name="apellido"
            value={formData.apellido}
            onChange={handleChange}
            placeholder="Ingrese el apellido"
          />
        </div>

        <div className="campo">
          <label>Documento:</label>
          <input
            type="text"
            name="documento"
            value={formData.documento}
            onChange={handleChange}
            placeholder="Ingrese el documento"
          />
        </div>

        <div className="campo">
          <label>Correo:</label>
          <input
            type="email"
            name="correo"
            value={formData.correo}
            onChange={handleChange}
            placeholder="Ingrese el correo"
          />
        </div>

        <div className="campo">
          <label>Teléfono:</label>
          <input
            type="text"
            name="telefono"
            value={formData.telefono}
            onChange={handleChange}
            placeholder="Ingrese el teléfono"
          />
        </div>

        <button type="submit" className="btn-principal">
          Registrar paciente
        </button>
      </form>

      <div className="tabla-container">
        <h2>Pacientes registrados</h2>

        {pacientes.length === 0 ? (
          <p>No hay pacientes registrados aún.</p>
        ) : (
          <table className="tabla">
            <thead>
              <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Documento</th>
                <th>Correo</th>
                <th>Teléfono</th>
              </tr>
            </thead>
            <tbody>
              {pacientes.map((paciente) => (
                <tr key={paciente.id}>
                  <td>{paciente.id}</td>
                  <td>{paciente.nombre}</td>
                  <td>{paciente.apellido}</td>
                  <td>{paciente.documento}</td>
                  <td>{paciente.correo}</td>
                  <td>{paciente.telefono}</td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </div>
  );
}

export default Pacientes;

