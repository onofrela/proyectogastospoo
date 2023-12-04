package registros;
import java.time.LocalDateTime;

import builder.BuilderTransaccion;
import cuentas.Cuenta;
import facade.Configuracion;

public class Transaccion extends Registro {
    private Cuenta cuentaOrigen;
    private Cuenta cuentaDestino;

    public Transaccion(LocalDateTime fecha, String descripcion, double monto, Configuracion configuracion, Cuenta cuentaOrigen, Cuenta cuentaDestino) {
        super(fecha, descripcion, monto, configuracion);
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
    
    public static class TransaccionBuilder implements BuilderTransaccion {
        private LocalDateTime fecha;
        private String descripcion = "";
        private double monto = 0;
        private Cuenta cuentaOrigen;
        private Cuenta cuentaDestino;
        private Configuracion configuracion;

        public TransaccionBuilder withFecha(LocalDateTime fecha) {
            this.fecha = fecha;
            return this;
        }

        public TransaccionBuilder withDescripcion(String descripcion) {
            this.descripcion = descripcion;
            return this;
        }

        public TransaccionBuilder withMonto(double monto) {
            this.monto = monto;
            return this;
        }

        public TransaccionBuilder withCuentaOrigen(Cuenta cuentaOrigen) {
            this.cuentaOrigen = cuentaOrigen;
            return this;
        }

        public TransaccionBuilder withCuentaDestino(Cuenta cuentaDestino) {
            this.cuentaDestino = cuentaDestino;
            return this;
        }

        public TransaccionBuilder withConfiguracion(Configuracion configuracion) {
            this.configuracion = configuracion;
            return this;
        }

        public Transaccion build() {
            if (fecha == null || configuracion == null) {
                throw new IllegalStateException("Fecha y Configuracion son campos requeridos.");
            } else if (cuentaOrigen == null) {
                throw new IllegalStateException("Cuenta de Origen es un campo requerido.");
            } else if (cuentaDestino == null) {
                throw new IllegalStateException("Cuenta de Destino es un campo requerido.");
            }

            return new Transaccion(fecha, descripcion, monto, configuracion, cuentaOrigen, cuentaDestino);
        }
    }
}
