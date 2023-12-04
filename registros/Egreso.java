package registros;

import cuentas.Cuenta;
import facade.Configuracion;

import java.awt.Color;
import java.time.LocalDateTime;

import javax.swing.ImageIcon;

import builder.BuilderEgreso;
import categoria.Categoria;

public class Egreso extends Registro {
    private Categoria categoria;
    private Cuenta cuentaAsociada;

    public Egreso(LocalDateTime fecha, String descripcion, double monto, Configuracion configuracion, Categoria categoria, Cuenta cuentaAsociada) {
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

        String linea = "Egreso:\n";
        linea += "\tCategoría: " + getCategoria() + "\n";
        linea += super.toString();

        return linea;
    }
    
    public static class EgresoBuilder implements BuilderEgreso {
        private LocalDateTime fecha;
        private String descripcion = "";
        private double monto = 0;
        private Categoria categoria;
        private Cuenta cuentaAsociada;
        private Configuracion configuracion;

        public EgresoBuilder withFecha(LocalDateTime fecha) {
            this.fecha = fecha;
            return this;
        }

        public EgresoBuilder withDescripcion(String descripcion) {
            this.descripcion = descripcion;
            return this;
        }

        public EgresoBuilder withMonto(double monto) {
            this.monto = monto;
            return this;
        }

        public EgresoBuilder withCategoria(Categoria categoria) {
            this.categoria = categoria;
            return this;
        }

        public EgresoBuilder withCuentaAsociada(Cuenta cuentaAsociada) {
            this.cuentaAsociada = cuentaAsociada;
            return this;
        }

        public EgresoBuilder withConfiguracion(Configuracion configuracion) {
            this.configuracion = configuracion;
            return this;
        }

        public Egreso build() {
            if (fecha == null || configuracion == null) {
                throw new IllegalStateException("Fecha y Configuracion son campos requeridos.");
            } 
            
            if (categoria == null) {
                java.net.URL imgURL = getClass().getResource("/categoria/iconos/unknown.png");
                ImageIcon icono = new ImageIcon(imgURL);
                Categoria categoriaNula = new Categoria("Sin Categoria", icono, Color.WHITE);
                categoria = categoriaNula;
            }

            return new Egreso(fecha, descripcion, monto, configuracion, categoria, cuentaAsociada);
        }
    }
}
