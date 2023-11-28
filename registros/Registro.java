package registros;
import java.util.Date;
import java.io.Serializable;

public abstract class Registro implements Serializable {
    private Date fecha;
    private String descripcion;
    private double monto;

    public Registro(Date fecha, String descripcion, double monto) {
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        
        String linea = "\tFecha: " + getFecha() + "\n";
        linea += "\tMonto: " + getMonto() + "\n";
        linea += "\tDescripción: " + getDescripcion() + "\n";

        return linea;
    }
}
