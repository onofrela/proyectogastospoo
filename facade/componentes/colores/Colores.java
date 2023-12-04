package facade.componentes.colores;
import java.awt.Color;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Colores {
    public final static Color BLANCO = new Color(255, 255, 255);
    public final static Color NEGRO = new Color(0, 0, 0);
    public final static Color GRIS_100 = new Color(249, 250, 251);
    public final static Color GRIS_200 = new Color(243, 244, 246);
    public final static Color GRIS_300 = new Color(229, 231, 235);
    public final static Color GRIS_400 = new Color(209, 213, 219);
    public final static Color GRIS_500 = new Color(156, 163, 175);
    public final static Color GRIS_600 = new Color(107, 114, 128);
    public final static Color GRIS_700 = new Color(75, 85, 99);
    public final static Color GRIS_800 = new Color(55, 65, 81);
    public final static Color GRIS_900 = new Color(31, 41, 55);
    public final static Color ROJO_100 = new Color(254, 226, 226);
    public final static Color ROJO_200 = new Color(254, 202, 202);
    public final static Color ROJO_300 = new Color(252, 165, 165);
    public final static Color ROJO_400 = new Color(248, 113, 113);
    public final static Color ROJO_500 = new Color(239, 68, 68);
    public final static Color ROJO_600 = new Color(220, 38, 38);
    public final static Color ROJO_700 = new Color(185, 28, 28);
    public final static Color ROJO_800 = new Color(153, 27, 27);
    public final static Color ROJO_900 = new Color(127, 29, 29);
    public final static Color NARANJA_100 = new Color(255, 236, 209);
    public final static Color NARANJA_200 = new Color(254, 205, 129);
    public final static Color NARANJA_300 = new Color(253, 164, 81);
    public final static Color NARANJA_400 = new Color(251, 122, 40);
    public final static Color NARANJA_500 = new Color(245, 101, 13);
    public final static Color NARANJA_600 = new Color(211, 78, 5);
    public final static Color NARANJA_700 = new Color(161, 53, 0);
    public final static Color NARANJA_800 = new Color(135, 39, 0);
    public final static Color NARANJA_900 = new Color(110, 29, 0);
    public final static Color AMARILLO_100 = new Color(255, 251, 185);
    public final static Color AMARILLO_200 = new Color(254, 243, 118);
    public final static Color AMARILLO_300 = new Color(253, 230, 53);
    public final static Color AMARILLO_400 = new Color(252, 211, 15);
    public final static Color AMARILLO_500 = new Color(251, 191, 36);
    public final static Color AMARILLO_600 = new Color(217, 159, 13);
    public final static Color AMARILLO_700 = new Color(180, 122, 6);
    public final static Color AMARILLO_800 = new Color(146, 102, 0);
    public final static Color AMARILLO_900 = new Color(120, 81, 0);
    public final static Color VERDE_100 = new Color(209, 250, 229);
    public final static Color VERDE_200 = new Color(167, 243, 208);
    public final static Color VERDE_300 = new Color(110, 231, 183);
    public final static Color VERDE_400 = new Color(52, 211, 153);
    public final static Color VERDE_500 = new Color(16, 185, 129);
    public final static Color VERDE_600 = new Color(5, 150, 105);
    public final static Color VERDE_700 = new Color(4, 120, 87);
    public final static Color VERDE_800 = new Color(6, 95, 70);
    public final static Color VERDE_900 = new Color(6, 78, 59);
    public final static Color AZUL_100 = new Color(202, 240, 248);
    public final static Color AZUL_200 = new Color(131, 219, 242);
    public final static Color AZUL_300 = new Color(64, 186, 255);
    public final static Color AZUL_400 = new Color(32, 140, 255);
    public final static Color AZUL_500 = new Color(25, 115, 255);
    public final static Color AZUL_600 = new Color(24, 98, 215);
    public final static Color AZUL_700 = new Color(21, 83, 182);
    public final static Color AZUL_800 = new Color(19, 66, 138);
    public final static Color AZUL_900 = new Color(17, 50, 104);
    public final static Color INDIGO_100 = new Color(211, 213, 255);
    public final static Color INDIGO_200 = new Color(159, 168, 255);
    public final static Color INDIGO_300 = new Color(121, 134, 203);
    public final static Color INDIGO_400 = new Color(92, 107, 192);
    public final static Color INDIGO_500 = new Color(76, 86, 182);
    public final static Color INDIGO_600 = new Color(64, 74, 162);
    public final static Color INDIGO_700 = new Color(55, 65, 146);
    public final static Color INDIGO_800 = new Color(48, 58, 128);
    public final static Color INDIGO_900 = new Color(37, 46, 99);
    public final static Color VIOLETA_100 = new Color(237, 233, 254);
    public final static Color VIOLETA_200 = new Color(207, 195, 255);
    public final static Color VIOLETA_300 = new Color(179, 136, 255);
    public final static Color VIOLETA_400 = new Color(148, 80, 255);
    public final static Color VIOLETA_500 = new Color(126, 62, 255);
    public final static Color VIOLETA_600 = new Color(103, 54, 211);
    public final static Color VIOLETA_700 = new Color(87, 45, 175);
    public final static Color VIOLETA_800 = new Color(74, 38, 147);
    public final static Color VIOLETA_900 = new Color(63, 32, 124);
    public final static Color ROSA_100 = new Color(255, 229, 243);
    public final static Color ROSA_200 = new Color(255, 187, 219);
    public final static Color ROSA_300 = new Color(252, 135, 192);
    public final static Color ROSA_400 = new Color(245, 101, 169);
    public final static Color ROSA_500 = new Color(237, 76, 157);
    public final static Color ROSA_600 = new Color(219, 39, 119);
    public final static Color ROSA_700 = new Color(186, 29, 96);
    public final static Color ROSA_800 = new Color(153, 27, 78);
    public final static Color ROSA_900 = new Color(126, 26, 61);

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
