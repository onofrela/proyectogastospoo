package cuentas;
import java.io.Serializable;

import monto.FormatoMonto;
import monto.Monto;

public class Cuenta implements Serializable {
    private String nombre;
    private Monto saldo;

    // Constructor
    public Cuenta(String nombre, double saldo, FormatoMonto formatoMonto) {
        this.nombre = nombre;
        this.saldo = new Monto(saldo, formatoMonto);
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSaldo() {
        return this.saldo.getMonto();
    }

    public Monto getSaldoFormateado() {
        return this.saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo.setMonto(saldo);
    }

    public void actualizarBalance(double monto) {
        this.saldo.setMonto(this.getSaldo() + monto);
    }

    @Override
    public String toString() {
        return "Cuenta: " + getNombre() + "\nSaldo: $" + this.saldo + "\n";
    }    
}
