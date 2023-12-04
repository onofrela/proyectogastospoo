package facade;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import facade.componentes.TopBar;
import facade.estilos.Estilos;

public class GestorFechas {
    private JPanel panel;
    private ActionListener menuAVolver;
    private Configuracion configuracion;

    public GestorFechas(JPanel panel, ActionListener menuAVolver, Configuracion configuracion){
        this.menuAVolver = menuAVolver;
        this.panel = panel;
        this.configuracion = configuracion;
    }

    public void configurarFormatoFecha() {
        panel.removeAll();
        panel.setLayout(new BorderLayout());
        TopBar.crearTopBar("Elige el formato de fecha", menuAVolver, panel);
    
        JPanel pnlBotones = new JPanel();
        pnlBotones.setLayout(new GridLayout(0, 2, 50, 50));
    
        generarBoton("dd/MM/yyyy", e -> {this.configuracion.setPatron("dd/MM/yyyy");menuAVolver.actionPerformed(null);}, pnlBotones);
        generarBoton("yyyy-MM-dd", e -> {this.configuracion.setPatron("yyyy-MM-dd");menuAVolver.actionPerformed(null);}, pnlBotones);
        generarBoton("dd/MMM/yyyy", e -> {this.configuracion.setPatron("dd/MMM/yyyy");menuAVolver.actionPerformed(null);}, pnlBotones);
        generarBoton("MMMM dd, yyyy", e -> {this.configuracion.setPatron("MMMM dd, yyyy");menuAVolver.actionPerformed(null);}, pnlBotones);
        generarBoton("dd 'de' MMMM 'del' yyyy", e -> {this.configuracion.setPatron("dd 'de' MMMM 'del' yyyy");menuAVolver.actionPerformed(null);}, pnlBotones);

        panel.add(pnlBotones, BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
    }   
    
    public JButton generarBoton(String texto, ActionListener listener, JPanel panel) {
        JButton boton = new JButton(texto);
        boton.addActionListener(listener);
        Estilos.estilizarBoton(boton);
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setAlignmentY(Component.CENTER_ALIGNMENT);
        panel.add(boton);
        return boton;
    }
}
