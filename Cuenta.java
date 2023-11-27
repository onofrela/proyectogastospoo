import java.io.Serializable;

public class Cuenta implements Serializable {
    private String nombre;
    private double saldo;

    // Constructor
    public Cuenta(String nombre, double saldo) {
        this.nombre = nombre;
        this.saldo = saldo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void actualizarBalance(double monto) {
        this.saldo += monto;
    }

    @Override
    public String toString() {
        return String.format("Cuenta{nombre='%s', saldo=%.2f}", nombre, saldo);
    }
}
