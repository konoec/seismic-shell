package pe.edu.utp.seguridad;

public class Validadores {
    // Validador para el ingreso de año válido (1960 - 2023)
    public static boolean esAnioValido(int year) throws Exception {
        if (year < 1960 || year > 2023) {
            String msg = "Error: El año debe estar entre 1960 y 2023.";
            throw new Exception(msg);
        }
        return true;
    }

    // Validador para el ingreso de magnitudes válidas (positivas y rango coherente)
    public static boolean sonMagnitudesValidas(double minMagnitude, double maxMagnitude) throws Exception {
        if (minMagnitude < 0 || maxMagnitude < 0 || minMagnitude > maxMagnitude) {
            String msg = "Error: Las magnitudes deben ser positivas y la magnitud máxima debe ser mayor o igual a la mínima.";
            throw new Exception(msg);
        }
        return true;
    }
}
