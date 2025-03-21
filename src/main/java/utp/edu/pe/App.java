package utp.edu.pe;

import utp.edu.pe.seguridad.ErrorLog;
import utp.edu.pe.vistas.Modulos;

import java.io.IOException;
import java.util.Scanner;

public class App {
    static String msg;
    static String lugar = "App";
    static ErrorLog errorLog;

    // Bloque estático para inicializar el objeto ErrorLog
    static {
        try {
            errorLog = new ErrorLog();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in); // Crear un objeto Scanner para la entrada del usuario
        int option; // Variable para almacenar la opción seleccionada por el usuario

        // Llamar al método loginMenu de la clase Modulos para autenticar al usuario
        String username = Modulos.loginMenu(scanner, errorLog);

        // Verificar si las credenciales son incorrectas
        if (username == null) {
            msg = "Credenciales incorrectas. Fin del programa.";
            System.out.println(msg); // Imprimir mensaje en consola
            errorLog.log(msg, ErrorLog.Level.INFO, lugar); // Registrar el mensaje en el archivo de log
            scanner.close(); // Cerrar el objeto Scanner
            return; // Salir del método main
        }

        errorLog.setUsuario(username); // Establecer el nombre de usuario en el objeto ErrorLog

        // Ciclo do-while para mostrar el menú principal y procesar las opciones del usuario
        do {
            Modulos.printMenuPrincipal(); // Mostrar el menú principal
            option = scanner.nextInt(); // Leer la opción ingresada por el usuario

            switch (option) {
                case 1:
                    Modulos.eventosPorRangoDeAnios(scanner, errorLog); // Llamar al método correspondiente en Modulos
                    break;
                case 2:
                    Modulos.eventosPorMes(scanner, errorLog); // Llamar al método correspondiente en Modulos
                    break;
                case 3:
                    Modulos.eventosPorMesYRangoDeMagnitudes(scanner, errorLog); // Llamar al método correspondiente en Modulos
                    break;
                case 4:
                    Modulos.eventosPorHora(scanner, errorLog); // Llamar al método correspondiente en Modulos
                    break;
                case 0:
                    System.out.println("Fin del programa."); // Mensaje de salida del programa
                    break;
                default:
                    msg = "Opción no válida. Por favor, ingrese una opción entre 0 y 4.";
                    errorLog.log(msg, ErrorLog.Level.WARN, lugar); // Registrar mensaje de advertencia en el archivo de log
                    System.out.println(msg); // Imprimir mensaje de error en consola
            }

            System.out.println(); // Imprimir línea en blanco para separar visualmente las iteraciones del menú
        } while (option != 0); // Continuar el ciclo mientras la opción no sea 0 (salir)

        scanner.close(); // Cerrar el objeto Scanner al salir del ciclo
    }
}
