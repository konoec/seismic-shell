package utp.edu.pe.vistas;

public class Reportes {
    // Array estático para los nombres de los meses
    private static String[] nombresMeses = {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO",
            "JULIO", "AGOSTO", "SETIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"};

    // Método para generar reporte de eventos por rango de años
    public static void eventosPorRangoDeAnios(int startYear, int endYear, int[] eventosPorMes, double[] porcentajePorMes){
        // Calcular el total de eventos y el porcentaje total
        int totalEventos = 0;
        for (int eventos : eventosPorMes) {
            totalEventos += eventos;
        }

        // Imprimir el encabezado y los detalles de cada mes
        System.out.printf("Reporte A: Tabla con el número de eventos sísmicos por mes dado un rango de años (%d - %d)\n",startYear, endYear);
        imprimirEncabezado();
        imprimirDetalles(eventosPorMes, porcentajePorMes);
        imprimirTotales(totalEventos);
    }

    // Método para generar reporte de eventos por mes dado un año
    public static void eventosPorMes(int year, int[] eventosPorMes, double[] porcentajePorMes){
        // Calcular el total de eventos y el porcentaje total
        int totalEventos = 0;
        for (int eventos : eventosPorMes) {
            totalEventos += eventos;
        }

        // Imprimir el encabezado y los detalles de cada mes
        System.out.printf("Reporte B: Tabla con el número de eventos sísmicos por mes dado un año (%d)\n", year);
        imprimirEncabezado();
        imprimirDetalles(eventosPorMes, porcentajePorMes);
        imprimirTotales(totalEventos);
    }

    // Método para generar reporte de eventos por mes y rango de magnitudes en un año
    public static void eventosPorMesYRangoDeMagnitudes(double minMagnitude, double maxMagnitude, int year, int[] eventosPorMes, double[] porcentajePorMes){
        // Calcular el total de eventos y el porcentaje total
        int totalEventos = 0;
        for (int eventos : eventosPorMes) {
            totalEventos += eventos;
        }

        // Imprimir el encabezado y los detalles de cada mes
        System.out.printf("Reporte C: Tabla con el número de eventos sísmicos por mes dados un rango de magnitudes (%.1f - %.1f) y un año (%d)\n", minMagnitude, maxMagnitude, year);
        imprimirEncabezado();
        imprimirDetalles(eventosPorMes, porcentajePorMes);
        imprimirTotales(totalEventos);
    }

    // Método para generar reporte de eventos por hora en un año
    public static void eventosPorHora(int year, int[] eventosPorHora){
        // Calcular el total de eventos
        int totalEventos = 0;
        for (int eventos : eventosPorHora) {
            totalEventos += eventos;
        }

        // Imprimir el encabezado y los detalles de cada hora
        System.out.printf("Reporte D: Tabla con el número de eventos sísmicos por hora en un año (%d)\n", year);
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("Hora   Eventos");
        System.out.println("----------------------------------------------------------------------------------------------------");
        for (int i = 0; i < eventosPorHora.length; i++) {
            String hora = String.format("%02d:00", i);
            int eventos = eventosPorHora[i];

            System.out.printf("%-7s %d\n", hora, eventos);
        }
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.printf("Total  %d\n", totalEventos);
        System.out.print("----------------------------------------------------------------------------------------------------");
    }

    // Método privado para imprimir el encabezado del reporte
    private static void imprimirEncabezado() {
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("Nº  MES         FREC    PORC");
        System.out.println("----------------------------------------------------------------------------------------------------");
    }

    // Método privado para imprimir los detalles de cada mes
    private static void imprimirDetalles(int[] eventosPorMes, double[] porcentajePorMes) {
        for (int i = 0; i < eventosPorMes.length; i++) {
            String numeroMes = String.format("%02d", i + 1);
            String nombreMes = nombresMeses[i];
            int frecuencia = eventosPorMes[i];
            double porcentaje = porcentajePorMes[i];

            System.out.printf("%s  %-10s  %-5d  %.2f%%\n", numeroMes, nombreMes, frecuencia, porcentaje);
        }
    }

    // Método privado para imprimir los totales del reporte
    private static void imprimirTotales(int totalEventos) {
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.printf("TOTAL           %-5d  %.2f%%\n", totalEventos, 100.00);
        System.out.print("----------------------------------------------------------------------------------------------------");
    }
}
