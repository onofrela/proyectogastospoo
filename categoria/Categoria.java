package categoria;

import java.awt.Color;
import java.io.Serializable;

import javax.swing.ImageIcon;

import builder.BuilderCategoria;

public class Categoria implements Serializable {
    private String nombre;
    private ImageIcon icono;
    private Color color;

    public Categoria(String nombre, ImageIcon icono, Color color) {
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

    public ImageIcon getIcono() {
        return this.icono;
    }

    public void setIcono(ImageIcon icono) {
        this.icono = icono;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString(){
        return "Nombre: " + getNombre();
    }
    
    public static class CategoriaBuilder implements BuilderCategoria {
        private String nombre;
        private ImageIcon icono;
        private Color color;

        public CategoriaBuilder withNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public CategoriaBuilder withIcono(ImageIcon icono) {
            this.icono = icono;
            return this;
        }

        public CategoriaBuilder withColor(Color color) {
            this.color = color;
            return this;
        }

        public Categoria build() {
            if (nombre == null || icono == null || color == null) {
                throw new IllegalStateException("Nombre, Icono y Color son campos requeridos.");
            }

            return new Categoria(nombre, icono, color);
        }
    }
}
