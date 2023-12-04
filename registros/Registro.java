package registros;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import monto.Monto;

import java.time.LocalDateTime;

public abstract class Registro implements Serializable {
    private String patron;
    private LocalDateTime fecha;
    private String idioma;
    private String pais;
    private String descripcion;
    private Monto monto;

    public Registro(LocalDateTime fecha, String descripcion, double monto) {
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.monto = new Monto(monto);
        this.patron = "dd 'de' MMMM 'del' yyyy";
        this.idioma = "es";
        this.pais = "ES";

    }

    public String obtenerFecha() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern(patron, new Locale(idioma, pais));
        return this.fecha.format(formato);
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getMonto() {
        return monto.getMonto();
    }

    public Monto getMontoFormateado() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto.setMonto(monto);
    }

    @Override
    public String toString() {
        
        String linea = "\tFecha: " + getFecha() + "\n";
        linea += "\tMonto: " + getMonto() + "\n";
        linea += "\tDescripción: " + getDescripcion() + "\n";

        return linea;
    }
}
