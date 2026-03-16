package Util;
// Clase que maneja un inicio de sesión básico con usuario y contraseña definidos en código
public class Login {

        // Credenciales correctas del sistema
    private String usuarioCorrecto = "admin";
    private String contrasenaCorrecta = "1234";

        // Método que recibe el usuario y la contraseña ingresados y valida si coinciden
    public boolean iniciarSesion(String usuarioString, String contrasenaString) {
        return usuarioString.equals(usuarioCorrecto) && contrasenaString.equals(contrasenaCorrecta);
        // Retorna true si ambos datos son correctos, y false si alguno no coincide    
    }
}

