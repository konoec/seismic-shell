package utp.edu.pe.servicios;

import utp.edu.pe.seguridad.ErrorLog;
import utp.edu.pe.utilidades.TextUTP;

import java.io.IOException;

import static utp.edu.pe.seguridad.ErrorLog.Level.ERROR;

public class ServicioExportarArchivos {
    static String lugar = "ServicioExportarArchivos";
    private static String[] nombresMeses = {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO",
            "JULIO", "AGOSTO", "SETIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"};

    // Método para generar reporte de eventos por rango de años
    public static void eventosPorRangoDeAnios(int startYear, int endYear, int[] eventosPorMes, double[] porcentajePorMes, ErrorLog errorLog) {
        StringBuilder sb = new StringBuilder();
        // Calcular el total de eventos y el porcentaje total
        int totalEventos = 0;
        for (int eventos : eventosPorMes) {
            totalEventos += eventos;
        }

        // Generar el contenido del reporte
        sb.append(String.format("Reporte A: Tabla con el número de eventos sísmicos por mes dado un rango de años (%d - %d)\n", startYear, endYear));
        sb.append("----------------------------------------------------------------------------------------------------\n");
        sb.append("Nº  MES         FREC    PORC\n");
        sb.append("----------------------------------------------------------------------------------------------------\n");
        for (int i = 0; i < eventosPorMes.length; i++) {
            String numeroMes = String.format("%02d", i + 1);
            String nombreMes = nombresMeses[i];
            int frecuencia = eventosPorMes[i];
            double porcentaje = porcentajePorMes[i];

            sb.append(String.format("%s  %-10s  %-5d  %.2f%%\n", numeroMes, nombreMes, frecuencia, porcentaje));
        }
        sb.append("----------------------------------------------------------------------------------------------------\n");
        sb.append(String.format("TOTAL           %-5d  %.2f%%\n", totalEventos, 100.00));
        sb.append("----------------------------------------------------------------------------------------------------\n");

        // Guardar en archivo
        try {
            TextUTP.append(sb.toString(), "src/main/resources/exportados/reportes.txt");
        } catch (IOException e) {
            errorLog.log(e.getMessage(), ERROR, lugar);
        }
    }

    // Método para generar reporte de eventos por mes en un año específico
    public static void eventosPorMes(int year, int[] eventosPorMes, double[] porcentajePorMes, ErrorLog errorLog) {
        StringBuilder sb = new StringBuilder();
        // Calcular el total de eventos y el porcentaje total
        int totalEventos = 0;
        for (int eventos : eventosPorMes) {
            totalEventos += eventos;
        }

        // Generar el contenido del reporte
        sb.append(String.format("Reporte B: Tabla con el número de eventos sísmicos por mes dado un año (%d)\n", year));
        sb.append("----------------------------------------------------------------------------------------------------\n");
        sb.append("Nº  MES         FREC    PORC\n");
        sb.append("----------------------------------------------------------------------------------------------------\n");
        for (int i = 0; i < eventosPorMes.length; i++) {
            String numeroMes = String.format("%02d", i + 1);
            String nombreMes = nombresMeses[i];
            int frecuencia = eventosPorMes[i];
            double porcentaje = porcentajePorMes[i];

            sb.append(String.format("%s  %-10s  %-5d  %.2f%%\n", numeroMes, nombreMes, frecuencia, porcentaje));
        }
        sb.append("----------------------------------------------------------------------------------------------------\n");
        sb.append(String.format("TOTAL           %-5d  %.2f%%\n", totalEventos, 100.00));
        sb.append("----------------------------------------------------------------------------------------------------\n");

        // Guardar en archivo
        try {
            TextUTP.append(sb.toString(), "src/main/resources/exportados/reportes.txt");
        } catch (IOException e) {
            errorLog.log(e.getMessage(), ERROR, lugar);
        }
    }

    // Método para generar reporte de eventos por mes y rango de magnitudes en un año específico
    public static void eventosPorMesYRangoDeMagnitudes(double minMagnitude, double maxMagnitude, int year, int[] eventosPorMes, double[] porcentajePorMes, ErrorLog errorLog) {
        StringBuilder sb = new StringBuilder();
        // Calcular el total de eventos y el porcentaje total
        int totalEventos = 0;
        for (int eventos : eventosPorMes) {
            totalEventos += eventos;
        }

        // Generar el contenido del reporte
        sb.append(String.format("Reporte C: Tabla con el número de eventos sísmicos por mes dados un rango de magnitudes (%.1f - %.1f) y un año (%d)\n", minMagnitude, maxMagnitude, year));
        sb.append("----------------------------------------------------------------------------------------------------\n");
        sb.append("Nº  MES         FREC    PORC\n");
        sb.append("----------------------------------------------------------------------------------------------------\n");
        for (int i = 0; i < eventosPorMes.length; i++) {
            String numeroMes = String.format("%02d", i + 1);
            String nombreMes = nombresMeses[i];
            int frecuencia = eventosPorMes[i];
            double porcentaje = porcentajePorMes[i];

            sb.append(String.format("%s  %-10s  %-5d  %.2f%%\n", numeroMes, nombreMes, frecuencia, porcentaje));
        }
        sb.append("----------------------------------------------------------------------------------------------------\n");
        sb.append(String.format("TOTAL           %-5d  %.2f%%\n", totalEventos, 100.00));
        sb.append("----------------------------------------------------------------------------------------------------\n");

        // Guardar en archivo
        try {
            TextUTP.append(sb.toString(), "src/main/resources/exportados/reportes.txt");
        } catch (IOException e) {
            errorLog.log(e.getMessage(), ERROR, lugar);
        }
    }

    // Método para generar reporte de eventos por hora en un año específico
    public static void eventosPorHora(int year, int[] eventosPorHora, ErrorLog errorLog) {
        StringBuilder sb = new StringBuilder();
        // Calcular el total de eventos
        int totalEventos = 0;
        for (int eventos : eventosPorHora) {
            totalEventos += eventos;
        }

        // Generar el contenido del reporte
        sb.append(String.format("Reporte D: Tabla con el número de eventos sísmicos por hora en un año (%d)\n", year));
        sb.append("----------------------------------------------------------------------------------------------------\n");
        sb.append("Hora   Eventos\n");
        sb.append("----------------------------------------------------------------------------------------------------\n");
        for (int i = 0; i < eventosPorHora.length; i++) {
            String hora = String.format("%02d:00", i);
            int eventos = eventosPorHora[i];

            sb.append(String.format("%-7s %d\n", hora, eventos));
        }
        sb.append("----------------------------------------------------------------------------------------------------\n");
        sb.append(String.format("Total  %d\n", totalEventos));
        sb.append("----------------------------------------------------------------------------------------------------\n");

        // Guardar en archivo
        try {
            TextUTP.append(sb.toString(), "src/main/resources/exportados/reportes.txt");
        } catch (IOException e) {
            errorLog.log(e.getMessage(), ERROR, lugar);
        }
    }
}
