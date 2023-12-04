package registros;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import facade.Configuracion;
import monto.Monto;

import java.time.LocalDateTime;

public abstract class Registro implements Serializable {
    private LocalDateTime fecha;
    private Configuracion configuracion;
    private String descripcion;
    private Monto monto;

    public Registro(LocalDateTime fecha, String descripcion, double monto, Configuracion configuracion) {
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.configuracion = configuracion;
        this.monto = new Monto(monto, this.configuracion);
    }

    public String obtenerFecha() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern(this.configuracion.getPatron(), new Locale(this.configuracion.getIdioma(), this.configuracion.getPais()));
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
