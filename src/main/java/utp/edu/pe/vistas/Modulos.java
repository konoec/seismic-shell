package pe.edu.utp.vistas;

import pe.edu.utp.objetos.RegistroSismico;
import pe.edu.utp.seguridad.ErrorLog;
import pe.edu.utp.seguridad.Validadores;
import pe.edu.utp.servicios.ServicioCargaDatos;
import pe.edu.utp.servicios.ServicioExportarArchivos;
import pe.edu.utp.servicios.ServicioLogin;
import pe.edu.utp.servicios.ServicioReportes;

import java.util.List;
import java.util.Scanner;

import static pe.edu.utp.seguridad.ErrorLog.Level.*;

public class Modulos {
    static String lugar = "Modulos";
    static String msg;

    public static void printMenuPrincipal() {
        String menu = """
            ------------------------------------------------------------------------------------
            MENU PRINCIPAL
            ------------------------------------------------------------------------------------
            1. Número de eventos sísmicos por año dado un rango de años.
            2. Número de eventos sísmicos por mes dado un año.
            3. Número de eventos sísmicos por mes dados un rango de magnitudes y un año.
            4. Número de eventos sísmicos por cada hora dado un año.
            0. FIN DEL PROGRAMA
            ------------------------------------------------------------------------------------
            Ingrese opción [1 – 4]:\s""";

        System.out.printf(menu);
    }

    public static String loginMenu(Scanner scanner) {

        String bienvenida = """
                ------------------------------------------------------------------------------------
                Bienvenido. Por favor, inicie sesión.
                ------------------------------------------------------------------------------------
                """;

        System.out.print(bienvenida);

        System.out.print("Ingrese su nombre de usuario: ");
        String username = scanner.next();

        System.out.print("Ingrese su contraseña: ");
        String password = scanner.next();

        if (ServicioLogin.validateCredentials(username, password)){
            return username;
        }

        return null;
    }

    public static void eventosPorRangoDeAnios(Scanner scanner, ErrorLog errorLog) {
        int startYear, endYear;

        // Solicitar al usuario que ingrese el rango de años
        do {
            try {
                System.out.println("------------------------------------------------------------------------------------");
                System.out.print("Ingrese el año de inicio (1960 - 2023): ");
                startYear = scanner.nextInt();
                Validadores.esAnioValido(startYear);
            } catch (Exception e) {
                msg = e.getMessage();
                System.out.println(msg);
                errorLog.log(msg, ERROR,lugar);
                continue;
            }

            try {
                System.out.println("------------------------------------------------------------------------------------");
                System.out.print("Ingrese el año de fin (1960 - 2023): ");
                endYear = scanner.nextInt();
                Validadores.esAnioValido(endYear);
                if (endYear < startYear) {
                    throw new Exception("Error: El año de fin no puede ser menor que el año de inicio.");
                }
                break; // Salir del bucle si todo está correcto
            } catch (Exception e) {
                msg = e.getMessage();
                errorLog.log(msg, ERROR,lugar);
                System.out.println(msg);
            }
        } while (true);

        do {

            int option;
            do {
                String menu = """
                    ------------------------------------------------------------------------------------
                    MÓDULO 01 – EVENTOS POR RANGO DE AÑOS: %d - %d
                    ------------------------------------------------------------------------------------
                    1. Imprimir por pantalla.
                    2. Exportar a archivo plano.
                    0. Volver al Menú Principal
                    ------------------------------------------------------------------------------------
                    Ingrese opción [1-2]:\s""";
                System.out.printf(menu, startYear, endYear);
                option = scanner.nextInt();

                Object[] resultados = ServicioReportes.obtenerEventosPorMes(startYear, endYear);

                int[] eventosPorMes = (int[]) resultados[0];
                double[] porcentajePorMes = (double[]) resultados[1];

                switch (option) {
                    case 1:
                        Reportes.eventosPorRangoDeAnios(startYear, endYear, eventosPorMes, porcentajePorMes);
                        break;
                    case 2:
                        ServicioExportarArchivos.eventosPorRangoDeAnios(startYear, endYear, eventosPorMes, porcentajePorMes);
                        break;
                    case 0:
                        System.out.println("Volviendo al Menú Principal...");
                        break;
                    default:
                        msg = "Opción no válida. Por favor, ingrese una opción entre 0 y 2.";
                        errorLog.log(msg, WARN,lugar);
                        System.out.println(msg);
                }

                System.out.println();
            } while (option != 0);
            break;
        } while (true);
    }

    public static void eventosPorMes(Scanner scanner, ErrorLog errorLog) {
        int year;

        // Solicitar al usuario que ingrese el año
        do {
            try {
                System.out.println("------------------------------------------------------------------------------------");
                System.out.print("Ingrese el año (1960 - 2023): ");
                year = scanner.nextInt();
                Validadores.esAnioValido(year);
                break; // Salir del bucle si todo está correcto
            } catch (Exception e) {
                msg = e.getMessage();
                errorLog.log(msg, ERROR,lugar);
                System.out.println(msg);
            }
        } while (true);

        int option;
        do {
            String menu = """
            ------------------------------------------------------------------------------------
            MÓDULO 02 – EVENTOS POR MES DADO UN AÑO: %d
            ------------------------------------------------------------------------------------
            1. Imprimir por pantalla.
            2. Exportar a archivo plano.
            0. Volver al Menú Principal
            ------------------------------------------------------------------------------------
            Ingrese opción [1-2]:\s""";
            System.out.printf(menu, year);
            option = scanner.nextInt();

            Object[] resultados = ServicioReportes.obtenerEventosPorMesParaUnAno(year);
            int[] eventosPorMes = (int[]) resultados[0];
            double[] porcentajePorMes = (double[]) resultados[1];

            switch (option) {
                case 1:
                    Reportes.eventosPorMes(year, eventosPorMes, porcentajePorMes);
                    break;
                case 2:
                    ServicioExportarArchivos.eventosPorMes(year, eventosPorMes, porcentajePorMes);
                    break;
                case 0:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    msg = "Opción no válida. Por favor, ingrese una opción entre 0 y 2.";
                    errorLog.log(msg, WARN,lugar);
                    System.out.println(msg);
            }

            System.out.println();
        } while (option != 0);
    }

    public static void eventosPorMesYRangoDeMagnitudes(Scanner scanner, ErrorLog errorLog) {
        int year;
        double minMagnitude, maxMagnitude;

        // Solicitar al usuario que ingrese el año
        do {
            try {
                System.out.println("------------------------------------------------------------------------------------");
                System.out.print("Ingrese el año (1960 - 2023): ");
                year = scanner.nextInt();
                Validadores.esAnioValido(year);
                break; // Salir del bucle si todo está correcto
            } catch (Exception e) {
                msg = e.getMessage();
                errorLog.log(msg, ERROR,lugar);
                System.out.println(msg);
            }
        } while (true);

        // Solicitar al usuario que ingrese el rango de magnitudes
        do {
            try {
                System.out.println("------------------------------------------------------------------------------------");
                System.out.print("Ingrese la magnitud mínima: ");
                minMagnitude = scanner.nextDouble();
                System.out.print("Ingrese la magnitud máxima: ");
                maxMagnitude = scanner.nextDouble();
                Validadores.sonMagnitudesValidas(minMagnitude, maxMagnitude);
                break; // Salir del bucle si todo está correcto
            } catch (Exception e) {
                msg = e.getMessage();
                errorLog.log(msg, ERROR,lugar);
                System.out.println(msg);
            }
        } while (true);

        int option;
        do {
            String menu = """
            ------------------------------------------------------------------------------------
            MÓDULO 03 – EVENTOS POR MES Y RANGO DE MAGNITUDES EN UN AÑO: Año %d - Magnitudes: %.1f a %.1f
            ------------------------------------------------------------------------------------
            1. Imprimir por pantalla.
            2. Exportar a archivo plano.
            0. Volver al Menú Principal
            ------------------------------------------------------------------------------------
            Ingrese opción [1-2]:\s""";
            System.out.printf(menu, year, minMagnitude, maxMagnitude);
            option = scanner.nextInt();

            // Llamada a la función y asignación de los resultados
            Object[] resultados = ServicioReportes.obtenerEventosPorMesParaAnoYRangoDeMagnitudes(year, minMagnitude, maxMagnitude);
            int[] eventosPorMes = (int[]) resultados[0];
            double[] porcentajePorMes = (double[]) resultados[1];

            switch (option) {
                case 1:
                    Reportes.eventosPorMesYRangoDeMagnitudes(minMagnitude, maxMagnitude, year, eventosPorMes, porcentajePorMes);
                    break;
                case 2:
                    ServicioExportarArchivos.eventosPorMesYRangoDeMagnitudes(minMagnitude, maxMagnitude, year, eventosPorMes, porcentajePorMes);
                    break;
                case 0:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    msg = "Opción no válida. Por favor, ingrese una opción entre 0 y 2.";
                    errorLog.log(msg, WARN,lugar);
                    System.out.println(msg);
            }

            System.out.println();
        } while (option != 0);
    }

    public static void eventosPorHora(Scanner scanner, ErrorLog errorLog) {
        int year;

        // Solicitar al usuario que ingrese el año
        do {
            try {
                System.out.println("------------------------------------------------------------------------------------");
                System.out.print("Ingrese el año (1960 - 2023): ");
                year = scanner.nextInt();
                Validadores.esAnioValido(year);
                break; // Salir del bucle si todo está correcto
            } catch (Exception e) {
                msg = e.getMessage();
                errorLog.log(msg, ERROR,lugar);
                System.out.println(msg);
            }
        } while (true);

        int option;
        do {
            String menu = """
            ------------------------------------------------------------------------------------
            MÓDULO 04 – EVENTOS POR HORA DADO UN AÑO: %d
            ------------------------------------------------------------------------------------
            1. Imprimir por pantalla.
            2. Exportar a archivo plano.
            0. Volver al Menú Principal
            ------------------------------------------------------------------------------------
            Ingrese opción [1-2]:\s""";
            System.out.printf(menu, year);
            option = scanner.nextInt();

            // Llamada a la función y asignación de los resultados
            int[] eventosPorHora = ServicioReportes.obtenerEventosPorHoraParaUnAno(year);

            switch (option) {
                case 1:
                    Reportes.eventosPorHora(year, eventosPorHora);
                    break;
                case 2:
                    ServicioExportarArchivos.eventosPorHora(year, eventosPorHora);
                    break;
                case 0:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    msg = "Opción no válida. Por favor, ingrese una opción entre 0 y 2.";
                    errorLog.log(msg, WARN,lugar);
                    System.out.println(msg);
            }

            System.out.println();
        } while (option != 0);
    }
}
