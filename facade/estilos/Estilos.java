package facade.estilos;

import javax.swing.*;
import java.awt.*;

public class Estilos {
    public static void estilizarBoton(JButton boton){
        boton.setBackground(new Color(8, 129, 163));
        boton.setForeground(new Color (249, 248, 237));
        boton.setFocusPainted(false); 
        boton.setBorderPainted(false); 
        boton.setOpaque(true); 
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        boton.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(31, 78, 95));
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(8, 129, 163));
            }
        });

        boton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK, 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
    }
}
