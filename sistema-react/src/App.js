import React, { useState } from "react";
import Sidebar from "./Components/Sidebar";
import Login from "./Pages/Login";
import Inicio from "./Pages/Inicio";
import Pacientes from "./Pages/Pacientes";
import Medicos from "./Pages/Medicos";
import Consultorios from "./Pages/Consultorios";
import Citas from "./Pages/Citas";
import HistoriaClinica from "./Pages/HistoriaClinica";
import "./Styles/App.css";

function App() {
  const [logueado, setLogueado] = useState(false);
  const [moduloActivo, setModuloActivo] = useState("inicio");

  const renderModulo = () => {
    switch (moduloActivo) {
      case "pacientes":
        return <Pacientes />;
      case "medicos":
        return <Medicos />;
      case "consultorios":
        return <Consultorios />;
      case "citas":
        return <Citas />;
      case "historia-clinica":
        return <HistoriaClinica />;
      case "inicio":
      default:
        return <Inicio />;
    }
  };

  if (!logueado) {
    return <Login onLogin={() => setLogueado(true)} />;
  }

  return (
    <div className="app-container">
      <Sidebar setModuloActivo={setModuloActivo} />
      <div className="content-container">{renderModulo()}</div>
    </div>
  );
}

export default App;