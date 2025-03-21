package pe.edu.utp.servicios;

import pe.edu.utp.objetos.RegistroSismico;
import pe.edu.utp.objetos.Usuario;
import pe.edu.utp.utilidades.TextUTP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServicioCargaDatos {
    public static List<RegistroSismico> cargarRegistrosSismicos() {

        String csvFile = "src/main/resources/dataset/Catalogo1960_2023.csv";
        // Lista para almacenar los registros
        List<RegistroSismico> registros = new ArrayList<>();

        try {
            List<String> lines = TextUTP.readlines(csvFile);

            // Iterar sobre cada línea del archivo CSV
            for (int i = 1; i < lines.size(); i++) { // Empezamos desde 1 para saltar la primera línea de encabezados
                String line = lines.get(i);
                String[] parts = line.split(";");

                int id = Integer.parseInt(parts[0]);
                String fechaUTC = parts[1];
                String horaUTC = parts[2];
                double latitud = Double.parseDouble(parts[3]);
                double longitud = Double.parseDouble(parts[4]);
                int profundidad = Integer.parseInt(parts[5]);
                double magnitud = Double.parseDouble(parts[6]);

                // Crear objeto RegistroSismico y agregarlo a la lista
                RegistroSismico registro = new RegistroSismico(id, fechaUTC, horaUTC, latitud, longitud,
                        profundidad, magnitud);
                registros.add(registro);
            }

            return registros;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Usuario> cargarUsuarios() {

        String usuariosFile = "src/main/resources/credenciales/usuarios.txt"; // Reemplaza con la ruta de tu archivo de usuarios

        try {
            List<String> lines = TextUTP.readlines(usuariosFile);

            // Lista para almacenar los usuarios
            List<Usuario> usuarios = new ArrayList<>();

            // Iterar sobre cada línea del archivo de usuarios
            for (String line : lines) {
                if (line.isEmpty()) {
                    continue; // Saltar líneas vacías, si las hubiera
                }
                String[] parts = line.split(":");

                String nombreUsuario = parts[0].trim();
                String contrasena = parts[1].trim();

                // Crear objeto Usuario y agregarlo a la lista
                Usuario usuario = new Usuario(nombreUsuario, contrasena);
                usuarios.add(usuario);
            }

            return usuarios;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
