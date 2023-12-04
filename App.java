import javax.swing.*;

import categoria.Categoria;
import cuentas.Cuenta;
import datos.ManejoArchivos;
import facade.*;
import monto.FormatoMonto;
import registros.Registro;

import java.awt.Image;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class App {
    private JFrame ventana;
    private JPanel panel;
    private Menu menu;
    private List<Registro> registros = new ArrayList<>();
    private List<Cuenta> cuentas = new ArrayList<>();
    private List<Categoria> categorias = new ArrayList<>();
    private Configuracion configuracion = new Configuracion(new FormatoMonto("$", "MXN"),"dd 'de' MMMM 'del' yyyy" , "es", "ES");

    public App() {
        initialize();
    }
    private void initialize() {
        ManejoArchivos.cargarDatos(this.registros, this.cuentas, this.categorias, this.configuracion);
        ventana = new JFrame("Registro de Gastos");
        ventana.setSize(1080, 720);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icon = new ImageIcon(getClass().getResource("/icon.png")).getImage();
        ventana.setIconImage(icon);
        ventana.setLocationRelativeTo(null);
    
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        this.menu = new Menu(this.ventana, this.panel, this.registros, this.cuentas, this.categorias, this.configuracion);
        ventana.add(panel);
    
        menu.mostrarMenuPrincipal(); 
        ventana.setVisible(true);

        ventana.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                salirDelPrograma();
            }
        });

    }

    private void salirDelPrograma() {
        ManejoArchivos.guardarDatos(this.registros, this.cuentas, this.categorias, this.configuracion);
        JOptionPane.showMessageDialog(null, "Saliendo de la aplicación. ¡Hasta luego!", "Cerrando aplicación", JOptionPane.PLAIN_MESSAGE);
        ventana.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::new);
    }
}
