package pe.edu.utp.servicios;

import pe.edu.utp.objetos.Usuario;

import java.util.List;

public class ServicioLogin {
    static List<Usuario> usuarios = ServicioCargaDatos.cargarUsuarios();
    public static boolean validateCredentials(String username, String password) {
        // Validar las credenciales contra la lista de usuarios cargados
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(username) && usuario.getContrasena().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
