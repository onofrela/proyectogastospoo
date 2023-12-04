package fecha;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import facade.Configuracion;

public class Fecha implements Serializable {
    private LocalDateTime fecha;
    private Configuracion configuracion;

    public Fecha(LocalDateTime fecha, Configuracion configuracion) {
        this.fecha = fecha;
        this.configuracion = configuracion;
    }
    
    public String obtenerFecha() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern(this.configuracion.getPatron(), new Locale(this.configuracion.getIdioma(), this.configuracion.getPais()));
        return this.fecha.format(formato);
    }

    public LocalDateTime getFecha() {
        return this.fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Configuracion getConfiguracion() {
        return this.configuracion;
    }

    public void setConfiguracion(Configuracion configuracion) {
        this.configuracion = configuracion;
    }
}
