package registros;
import java.time.LocalDateTime;

import cuentas.Cuenta;
import monto.FormatoMonto;

public class Transaccion extends Registro {
    private Cuenta cuentaOrigen;
    private Cuenta cuentaDestino;

    public Transaccion(LocalDateTime fecha, String descripcion, double monto, FormatoMonto formatoMonto, Cuenta cuentaOrigen, Cuenta cuentaDestino) {
        super(fecha, descripcion, monto, formatoMonto);
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
