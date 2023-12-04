package facade.componentes;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import facade.estilos.Estilos;

public class BotonCancelar {
    public static JButton crearBoton(ActionListener menuAVolver) {
        JButton btnCancelar = new JButton("Cancelar");
        Estilos.estilizarBoton(btnCancelar);
        btnCancelar.addActionListener(e -> menuAVolver.actionPerformed(null));
        return btnCancelar;
    }    
}
