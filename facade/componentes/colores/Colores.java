package facade.componentes.colores;
import java.awt.Color;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Colores {
    public final static Color ROJO = new Color(255, 51, 102);
    public final static Color NARANJA = new Color(255, 102, 51);
    public final static Color AMARILLO = new Color(255, 204, 51);
    public final static Color VERDE = new Color(51, 204, 51);
    public final static Color AZUL = new Color(51, 102, 255);
    public final static Color MORADO = new Color(153, 51, 204);
    public final static Color VIOLETA = new Color(204, 102, 255);
    public final static Color ROSA = new Color(255, 153, 204);

    public static Color[] obtenerColores() {
        List<Color> listaColores = new ArrayList<>();
        Field[] campos = Colores.class.getDeclaredFields(); // Obtener todos los campos de la clase

        for (Field campo : campos) {
            try {
                Object valor = campo.get(null);
                if (valor instanceof Color) {
                    listaColores.add((Color) valor);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return listaColores.toArray(new Color[0]);
    }
}
