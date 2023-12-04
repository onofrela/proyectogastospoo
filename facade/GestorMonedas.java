package facade;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import facade.componentes.TopBar;

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
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setAlignmentY(Component.CENTER_ALIGNMENT);
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
        
        panel.add(boton);
        return boton;
    }
}
