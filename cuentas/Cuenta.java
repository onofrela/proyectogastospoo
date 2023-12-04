package cuentas;
import java.io.Serializable;

import builder.BuilderCuenta;
import facade.Configuracion;
import monto.Monto;

public class Cuenta implements Serializable {
    private String nombre;
    private Monto saldo;

    public Cuenta(String nombre, double saldo, Configuracion configuracion) {
        this.nombre = nombre;
        this.saldo = new Monto(saldo, configuracion);
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

    public static class CuentaBuilder implements BuilderCuenta {
        private String nombre;
        private double saldo = 0; // Valor predeterminado
        private Configuracion configuracion;

        public CuentaBuilder withNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public CuentaBuilder withSaldo(double saldo) {
            this.saldo = saldo;
            return this;
        }

        public CuentaBuilder withConfiguracion(Configuracion configuracion) {
            this.configuracion = configuracion;
            return this;
        }

        public Cuenta build() {
            if (nombre == null || configuracion == null) {
                throw new IllegalStateException("Nombre y Configuracion son campos requeridos.");
            }

            return new Cuenta(nombre, saldo, configuracion);
        }
    }
}
