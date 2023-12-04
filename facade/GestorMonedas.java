package facade;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import facade.componentes.TopBar;
import facade.estilos.Estilos;

public class GestorMonedas {
    private JPanel panel;
    private ActionListener menuAVolver;
    private Configuracion configuracion;

    public GestorMonedas(JPanel panel, ActionListener menuAVolver, Configuracion configuracion){
        this.menuAVolver = menuAVolver;
        this.panel = panel;
        this.configuracion = configuracion;
    }

    public void configurarMoneda() {
        panel.removeAll();
        panel.setLayout(new BorderLayout());
        TopBar.crearTopBar("Elige la moneda a manejar", menuAVolver, panel);
    
        JPanel pnlBotones = new JPanel();
        pnlBotones.setLayout(new GridLayout(0, 2, 50, 50));
    
        generarBoton("Pesos Mexicanos", e -> {this.configuracion.getFormatoMonto().setSimboloMoneda("$");this.configuracion.getFormatoMonto().setMoneda("MXN");menuAVolver.actionPerformed(null);}, pnlBotones);
        generarBoton("Dólares Americanos", e -> {this.configuracion.getFormatoMonto().setSimboloMoneda("$");this.configuracion.getFormatoMonto().setMoneda("USD");menuAVolver.actionPerformed(null);}, pnlBotones);
        generarBoton("Euros", e -> {this.configuracion.getFormatoMonto().setSimboloMoneda("€");this.configuracion.getFormatoMonto().setMoneda("EUR");menuAVolver.actionPerformed(null);}, pnlBotones);
        generarBoton("Yenes", e -> {this.configuracion.getFormatoMonto().setSimboloMoneda("¥");this.configuracion.getFormatoMonto().setMoneda("JPY");menuAVolver.actionPerformed(null);}, pnlBotones);

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
