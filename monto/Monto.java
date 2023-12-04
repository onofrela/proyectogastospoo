package monto;

import java.io.Serializable;

public class Monto implements Serializable {
    private double monto;
    private String simboloMoneda;
    private String moneda;

    public Monto(double monto) {
        this.monto = monto;
        this.simboloMoneda = "$";
        this.moneda = "MXN";
    }

    public Monto(double monto, String simboloMoneda, String moneda) {
        this.monto = monto;
        this.simboloMoneda = simboloMoneda;
        this.moneda = moneda;
    }

    public double getMonto() {
        return this.monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getSimboloMoneda() {
        return this.simboloMoneda;
    }

    public void setSimboloMoneda(String simboloMoneda) {
        this.simboloMoneda = simboloMoneda;
    }

    public String getMoneda() {
        return this.moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    @Override
    public String toString() {
        return simboloMoneda + String.format("%.2f", monto) + " " + moneda;
    }
}
