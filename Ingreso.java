import java.util.Date;

public class Ingreso extends Registro {
    private String categoria;
    private Cuenta cuentaAsociada;

    public Ingreso(Date fecha, String descripcion, double monto, String categoria, Cuenta cuentaAsociada) {
        super(fecha, descripcion, monto);
        this.categoria = categoria;
        this.cuentaAsociada = cuentaAsociada;
    }

    public Cuenta getCuentaAsociada() {
        return this.cuentaAsociada;
    }

    public void setCuentaAsociada(Cuenta cuentaAsociada) {
        this.cuentaAsociada = cuentaAsociada;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return String.format("Ingreso{categoria='%s', %s}", categoria, super.toString());
    }
}
