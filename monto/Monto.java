package monto;

import java.io.Serializable;

import facade.Configuracion;

public class Monto implements Serializable {
    private double monto;
    private Configuracion configuracion;

    public Monto(double monto, Configuracion configuracion) {
        this.monto = monto;
        this.configuracion = configuracion;
    }

    public double getMonto() {
        return this.monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return this.configuracion.getFormatoMonto().getSimboloMoneda() + String.format("%.2f", this.monto) + " " + this.configuracion.getFormatoMonto().getMoneda();
    }
}
