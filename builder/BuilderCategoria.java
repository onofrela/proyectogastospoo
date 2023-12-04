package builder;

import categoria.Categoria;

import java.awt.Color;
import javax.swing.ImageIcon;

public interface BuilderCategoria {
    public abstract BuilderCategoria withNombre(String nombre);
    public abstract BuilderCategoria withIcono(ImageIcon icono);
    public abstract BuilderCategoria withColor(Color color);
    public abstract Categoria build();
}
