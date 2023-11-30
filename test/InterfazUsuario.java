package test;

import javax.swing.*;

import test.facade.*;

import java.awt.event.*;

public class InterfazUsuario {
    private JFrame ventana;
    private JPanel panel;
    private Menu menu;
    public InterfazUsuario() {
        initialize();
    }
    private void initialize() {
        ventana = new JFrame("Menú Principal");
        ventana.setSize(400, 300);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        panel = new JPanel();
        panel.setLayout(null);
        this.menu = new Menu(ventana, panel);
        ventana.add(panel);
    
        menu.mostrarMenuPrincipal(); // Mostrar el menú principal al iniciar
    
        ventana.setVisible(true);

        ventana.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                salirDelPrograma();
            }
        });

    }

    private void salirDelPrograma() {
        // Lógica para salir del programa
        JOptionPane.showMessageDialog(null, "Saliendo de la aplicación. ¡Hasta luego!");
        ventana.dispose(); // Cierra la ventana
        // Aquí podrías agregar la lógica para guardar los datos antes de salir
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InterfazUsuario::new);
    }
}
