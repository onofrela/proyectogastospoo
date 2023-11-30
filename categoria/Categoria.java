package categoria;

import java.io.Serializable;

public class Categoria implements Serializable {
    private String nombre;
    private String icono;
    private String color;

    public Categoria(String nombre, String icono, String color) {
        this.nombre = nombre;
        this.icono = icono;
        this.color = color;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIcono() {
        return this.icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    @Override
    public String toString(){
        return getNombre();
    }
}
