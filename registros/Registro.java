package registros;
import java.io.Serializable;
import java.time.LocalDateTime;

import facade.Configuracion;
import fecha.Fecha;
import monto.Monto;

public abstract class Registro implements Serializable {
    private Fecha fecha;

    private Configuracion configuracion;
    private String descripcion;
    private Monto monto;

    public Registro(LocalDateTime fecha, String descripcion, double monto, Configuracion configuracion) {
        this.descripcion = descripcion;
        this.configuracion = configuracion;
        this.fecha = new Fecha(fecha, this.configuracion);
        this.monto = new Monto(monto, this.configuracion);
    }

    public Fecha getFecha() {
        return this.fecha;
    }

    public void setFecha(Fecha fecha) {
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
        
        String linea = "\tFecha: " + this.fecha.getFecha() + "\n";
        linea += "\tMonto: " + getMonto() + "\n";
        linea += "\tDescripción: " + getDescripcion() + "\n";

        return linea;
    }
}
