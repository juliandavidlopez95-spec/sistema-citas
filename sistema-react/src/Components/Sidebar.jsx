import React from "react";

function Sidebar({ setModuloActivo }) {
  return (
    <aside className="sidebar">
      <h2 className="logo">Sistema de Citas</h2>

      <button onClick={() => setModuloActivo("inicio")}>Inicio</button>
      <button onClick={() => setModuloActivo("pacientes")}>Pacientes</button>
      <button onClick={() => setModuloActivo("medicos")}>Médicos</button>
      <button onClick={() => setModuloActivo("consultorios")}>Consultorios</button>
      <button onClick={() => setModuloActivo("citas")}>Citas</button>
      <button onClick={() => setModuloActivo("historia-clinica")}>Historia Clínica</button>
    </aside>
  );
}

export default Sidebar;

