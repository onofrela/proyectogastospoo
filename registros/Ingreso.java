package registros;

import cuentas.Cuenta;
import facade.Configuracion;

import java.time.LocalDateTime;

import categoria.Categoria;

public class Ingreso extends Registro {
    private Categoria categoria;
    private Cuenta cuentaAsociada;

    public Ingreso(LocalDateTime fecha, String descripcion, double monto, Configuracion configuracion, Categoria categoria, Cuenta cuentaAsociada) {
        super(fecha, descripcion, monto, configuracion);
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
        
        String linea = "Ingreso:\n";
        linea += "\tCategoría: " + getCategoria() + "\n";
        linea += super.toString();

        return linea;
    }
}
