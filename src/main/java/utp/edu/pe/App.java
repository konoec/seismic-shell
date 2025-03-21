package pe.edu.utp;

import pe.edu.utp.seguridad.ErrorLog;
import pe.edu.utp.vistas.Modulos;

import java.io.IOException;
import java.util.Scanner;

import static pe.edu.utp.seguridad.ErrorLog.Level.*;

public class App {
    static String msg;
    static String lugar = "App";
    static ErrorLog errorLog;

    static {
        try {
            errorLog = new ErrorLog();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int option;

        String username = Modulos.loginMenu(scanner);

        // Menú de inicio de sesión
        if (username == null) {
            msg = "Credenciales incorrectas. Fin del programa.";
            System.out.println(msg);
            errorLog.log(msg, INFO, lugar);
            scanner.close();
            return;
        }

        errorLog.setUsuario(username);

        do {
            Modulos.printMenuPrincipal();
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    Modulos.eventosPorRangoDeAnios(scanner, errorLog);
                    break;
                case 2:
                    Modulos.eventosPorMes(scanner, errorLog);
                    break;
                case 3:
                    Modulos.eventosPorMesYRangoDeMagnitudes(scanner, errorLog);
                    break;
                case 4:
                    Modulos.eventosPorHora(scanner, errorLog);
                    break;
                case 0:
                    System.out.println("Fin del programa.");
                    break;
                default:
                    msg = "Opción no válida. Por favor, ingrese una opción entre 0 y 4.";
                    errorLog.log(msg, WARN,lugar);
                    System.out.println(msg);
            }

            System.out.println();
        } while (option != 0);

        scanner.close();
    }
}
