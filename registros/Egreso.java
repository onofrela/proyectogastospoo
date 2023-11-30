package registros;
import java.util.Date;

import cuentas.Cuenta;
import categoria.Categoria;

public class Egreso extends Registro {
    private Categoria categoria;
    private Cuenta cuentaAsociada;

    public Egreso(Date fecha, String descripcion, double monto, Categoria categoria, Cuenta cuentaAsociada) {
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {

        String linea = "Egreso:\n";
        linea += "\tCategoría: " + getCategoria() + "\n";
        linea += super.toString();

        return linea;
    }
}
