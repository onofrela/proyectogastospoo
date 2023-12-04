package test.facade.componentes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BotonMenu {
    public static JButton crearBotonMenu(ActionListener menuAVolver) {
        java.net.URL imgURL = BotonRegreso.class.getResource("./iconos/home.png");
        ImageIcon icono = new ImageIcon(imgURL);
        JButton btnMenu = new JButton(icono);
        btnMenu.setPreferredSize(new Dimension(45, 45)); // Establecer el tamaño preferido
        btnMenu.addActionListener(menuAVolver);
        return btnMenu;
    }    
}

