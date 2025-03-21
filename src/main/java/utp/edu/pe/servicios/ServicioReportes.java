package utp.edu.pe.servicios;

import utp.edu.pe.objetos.RegistroSismico;
import utp.edu.pe.seguridad.ErrorLog;

import java.util.List;

public class ServicioReportes {

    // Método para obtener el número de eventos sísmicos por mes y su porcentaje
    public static Object[] obtenerEventosPorMes(int startYear, int endYear, ErrorLog errorLog) {

        List<RegistroSismico> registros = ServicioCargaDatos.cargarRegistrosSismicos(errorLog);

        int[] eventosPorMes = new int[12];
        int totalEventos = 0;

        // Contar eventos por mes dentro del rango de años
        for (RegistroSismico registro : registros) {
            int year = registro.getFechaUTC().getYear();
            if (year >= startYear && year <= endYear) {
                int month = registro.getFechaUTC().getMonthValue();
                eventosPorMes[month - 1]++;
                totalEventos++;
            }
        }

        // Calcular el porcentaje de eventos por mes
        double[] porcentajePorMes = new double[12];
        for (int i = 0; i < eventosPorMes.length; i++) {
            porcentajePorMes[i] = (eventosPorMes[i] * 100.0) / totalEventos;
        }

        // Retornar los resultados como un objeto array
        return new Object[]{eventosPorMes, porcentajePorMes};
    }

    // Método para obtener el número de eventos sísmicos por mes y su porcentaje para un año
    public static Object[] obtenerEventosPorMesParaUnAno(int year, ErrorLog errorLog) {

        List<RegistroSismico> registros = ServicioCargaDatos.cargarRegistrosSismicos(errorLog);

        int[] eventosPorMes = new int[12];
        int totalEventos = 0;

        // Contar eventos por mes dentro del año especificado
        for (RegistroSismico registro : registros) {
            if (registro.getFechaUTC().getYear() == year) {
                int month = registro.getFechaUTC().getMonthValue();
                eventosPorMes[month - 1]++;
                totalEventos++;
            }
        }

        // Calcular el porcentaje de eventos por mes
        double[] porcentajePorMes = new double[12];
        for (int i = 0; i < eventosPorMes.length; i++) {
            porcentajePorMes[i] = (eventosPorMes[i] * 100.0) / totalEventos;
        }

        // Retornar los resultados como un objeto array
        return new Object[]{eventosPorMes, porcentajePorMes};
    }

    // Método para obtener el número de eventos sísmicos por mes y su porcentaje para un año y rango de magnitudes
    public static Object[] obtenerEventosPorMesParaAnoYRangoDeMagnitudes(int year, double minMagnitude, double maxMagnitude, ErrorLog errorLog) {

        List<RegistroSismico> registros = ServicioCargaDatos.cargarRegistrosSismicos(errorLog);

        int[] eventosPorMes = new int[12];
        int totalEventos = 0;

        // Contar eventos por mes dentro del año especificado y el rango de magnitudes
        for (RegistroSismico registro : registros) {
            if (registro.getFechaUTC().getYear() == year && registro.getMagnitud() >= minMagnitude && registro.getMagnitud() <= maxMagnitude) {
                int month = registro.getFechaUTC().getMonthValue();
                eventosPorMes[month - 1]++;
                totalEventos++;
            }
        }

        // Calcular el porcentaje de eventos por mes
        double[] porcentajePorMes = new double[12];
        for (int i = 0; i < eventosPorMes.length; i++) {
            porcentajePorMes[i] = (eventosPorMes[i] * 100.0) / totalEventos;
        }

        // Retornar los resultados como un objeto array
        return new Object[]{eventosPorMes, porcentajePorMes};
    }

    // Método para obtener el número de eventos sísmicos por cada hora en un año
    public static int[] obtenerEventosPorHoraParaUnAno(int year, ErrorLog errorLog) {

        List<RegistroSismico> registros = ServicioCargaDatos.cargarRegistrosSismicos(errorLog);

        int[] eventosPorHora = new int[24];

        // Contar eventos por hora dentro del año especificado
        for (RegistroSismico registro : registros) {
            if (registro.getFechaUTC().getYear() == year) {
                int hour = registro.getHoraUTC().getHour();
                eventosPorHora[hour]++;
            }
        }

        // Retornar el array de eventos por hora
        return eventosPorHora;
    }
}
