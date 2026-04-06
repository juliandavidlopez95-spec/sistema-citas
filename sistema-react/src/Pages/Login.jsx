import React, { useState } from "react";

function Login({ onLogin }) {
  const [credenciales, setCredenciales] = useState({
    usuario: "",
    contrasena: "",
  });

  const [mensaje, setMensaje] = useState("");

  const handleChange = (e) => {
    const { name, value } = e.target;
    setCredenciales({
      ...credenciales,
      [name]: value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (
      credenciales.usuario === "admin" &&
      credenciales.contrasena === "12345"
    ) {
      setMensaje("Inicio de sesión exitoso.");
      onLogin();
    } else {
      setMensaje(
        "Credenciales incorrectas. Por favor, contacte al administrador de la aplicación en bloodinked@outlook.com."
      );
    }
  };

  const handleRecuperarContrasena = () => {
    alert(
      "Se te enviará un correo de reposición a tu usuario basado en tu correo, si este se encuentra registrado en nuestro sistema. En caso contrario, por favor contacte con los administradores en bloodinked@outlook.com."
    );
  };

  return (
    <div className="login-container">
      <div className="login-card">
        <h1>Inicio de sesión</h1>
        <p>
          Ingresa con tus credenciales para acceder al sistema de gestión de
          citas médicas.
        </p>

        <form className="formulario" onSubmit={handleSubmit}>
          <div className="campo">
            <label>Usuario</label>
            <input
              type="text"
              name="usuario"
              value={credenciales.usuario}
              onChange={handleChange}
              placeholder="Ingrese su usuario"
            />
          </div>

          <div className="campo">
            <label>Contraseña</label>
            <input
              type="password"
              name="contrasena"
              value={credenciales.contrasena}
              onChange={handleChange}
              placeholder="Ingrese su contraseña"
            />
          </div>

          <button type="submit" className="btn-principal">
            Ingresar
          </button>
        </form>

        <button
          type="button"
          className="btn-secundario"
          onClick={handleRecuperarContrasena}
        >
          Reiniciar contraseña
        </button>

        <p className="mensaje-ayuda">
          Si presenta inconvenientes de acceso, contacte al administrador de la
          aplicación en <strong>bloodinked@outlook.com</strong>.
        </p>

        {mensaje && <p className="mensaje-confirmacion">{mensaje}</p>}
      </div>
    </div>
  );
}

export default Login;