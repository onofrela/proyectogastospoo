package monto;

import java.io.Serializable;

public class Monto implements Serializable {
    private double monto;
    private FormatoMonto formato;

    public Monto(double monto, FormatoMonto formato) {
        this.monto = monto;
        this.formato = formato;
    }

    public double getMonto() {
        return this.monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return formato.getSimboloMoneda() + String.format("%.2f", monto) + " " + formato.getMoneda();
    }
}
