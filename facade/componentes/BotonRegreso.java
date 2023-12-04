package facade.componentes;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import facade.estilos.Estilos;

public class BotonRegreso {
    public static JButton crearBotonRegreso(ActionListener menuAVolver) {
        java.net.URL imgURL = BotonRegreso.class.getResource("./iconos/back.png");
        ImageIcon icono = new ImageIcon(imgURL);
        JButton btnVolver = new JButton(icono);
        btnVolver.setPreferredSize(new Dimension(50, 50)); // Establecer el tamaño preferido
        Estilos.estilizarBoton(btnVolver);
        btnVolver.addActionListener(menuAVolver);
        return btnVolver;
    }    
}
