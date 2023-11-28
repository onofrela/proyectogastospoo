package registros;
import java.util.Date;

import cuentas.Cuenta;

public class Transaccion extends Registro {
    private Cuenta cuentaOrigen;
    private Cuenta cuentaDestino;

    public Transaccion(Date fecha, String descripcion, double monto, Cuenta cuentaOrigen, Cuenta cuentaDestino) {
        super(fecha, descripcion, monto);
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
    }

    public Cuenta getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(Cuenta cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public Cuenta getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(Cuenta cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    @Override
    public String toString() {
        
        String linea = "Transacción:\n";
        linea += "\tCuenta de origen: " + getCuentaOrigen() + "\n";
        linea += "\tCuenta destino: " + getCuentaDestino() + "\n";
        linea += super.toString();

        return linea;
    }
}
