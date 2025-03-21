package utp.edu.pe.objetos;

import java.time.LocalDate;
import java.time.LocalTime;

public class RegistroSismico {
    private int id;
    private LocalDate fechaUTC;
    private LocalTime horaUTC;
    private double latitud;
    private double longitud;
    private int profundidad;
    private double magnitud;

    // Constructor
    public RegistroSismico(int id, String fechaUTC, String horaUTC, double latitud, double longitud,
                           int profundidad, double magnitud) {
        this.id = id;
        this.fechaUTC = parseFechaUTC(fechaUTC);
        this.horaUTC = parseHoraUTC(horaUTC);
        this.latitud = latitud;
        this.longitud = longitud;
        this.profundidad = profundidad;
        this.magnitud = magnitud;
    }

    // Métodos getters
    public int getId() {
        return id;
    }

    public LocalDate getFechaUTC() {
        return fechaUTC;
    }

    public LocalTime getHoraUTC() {
        return horaUTC;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public int getProfundidad() {
        return profundidad;
    }

    public double getMagnitud() {
        return magnitud;
    }


    // Métodos privados para parsear fecha y hora
    private LocalDate parseFechaUTC(String fechaUTC) {
        int year = Integer.parseInt(fechaUTC.substring(0, 4));
        int month = Integer.parseInt(fechaUTC.substring(4, 6));
        int day = Integer.parseInt(fechaUTC.substring(6, 8));
        return LocalDate.of(year, month, day);
    }

    private LocalTime parseHoraUTC(String horaUTC) {
        int hour = Integer.parseInt(horaUTC.substring(0, 2));
        int minute = Integer.parseInt(horaUTC.substring(2, 4));
        int second = Integer.parseInt(horaUTC.substring(4, 6));
        return LocalTime.of(hour, minute, second);
    }
}
