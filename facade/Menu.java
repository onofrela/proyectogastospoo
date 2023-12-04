package facade;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import categoria.Categoria;
import cuentas.Cuenta;
import datos.ManejoArchivos;
import registros.Registro;
import facade.componentes.TopBar;
import facade.estilos.Estilos;
import monto.FormatoMonto;

public class Menu {
    public JLabel lblBalance;
    public JPanel panel;
    public JFrame ventana;

    private static List<Registro> registros = new ArrayList<>();
    private static List<Cuenta> cuentas = new ArrayList<>();
    private static List<Categoria> categorias = new ArrayList<>();

    private static GestorCuentas gestorCuentas;
    private static GestorCategorias gestorCategorias;
    private static GestorRegistros gestorRegistros;
    private static GestorFechas gestorFechas;
    private static GestorMonedas gestorMonedas;
    private static Configuracion configuracion;


    private Balance balance;

    public Menu(JFrame ventana, JPanel panel){
        this.ventana = ventana;
        this.panel = panel;
        configuracion = new Configuracion(new FormatoMonto("$", "MXN"),"dd 'de' MMMM 'del' yyyy" , "es", "ES");
        ManejoArchivos.cargarDatos(registros, cuentas, categorias, configuracion);
        this.balance = new Balance(cuentas, configuracion);
        gestorCuentas = new GestorCuentas(cuentas, e -> mostrarMenuCuentas(), panel, configuracion);
        gestorCategorias = new GestorCategorias(categorias, e -> mostrarMenuCategorias(), panel);
        gestorRegistros = new GestorRegistros(gestorCategorias, gestorCuentas, registros, panel, e -> mostrarMenuRegistros(), configuracion);
        gestorMonedas = new GestorMonedas(panel, e -> mostrarMenuConfiguracion(), configuracion);
        gestorFechas = new GestorFechas(panel, e -> mostrarMenuConfiguracion(), configuracion);
    }

    public JButton generarBoton(String texto, ActionListener listener, JPanel panel) {
        JButton boton = new JButton(texto);
        boton.addActionListener(listener);
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setAlignmentY(Component.CENTER_ALIGNMENT);
        Estilos.estilizarBoton(boton);
        
        panel.add(boton);
        return boton;
    }
    
    public void mostrarMenuPrincipal() {
        panel.removeAll();
        panel.setLayout(new BorderLayout());
    
        panel.add(this.balance.generarBalance(), BorderLayout.PAGE_START);
        JPanel pnlBotones = new JPanel();
        pnlBotones.setLayout(new GridLayout(0, 2, 50, 30));
    
        generarBoton("Ver Registros", e -> mostrarMenuRegistros(), pnlBotones);
        generarBoton("Gestionar Cuentas", e -> mostrarMenuCuentas(), pnlBotones);
        generarBoton("Gestionar Categorías", e -> mostrarMenuCategorias(), pnlBotones);
        generarBoton("Agregar Registro", e -> gestorRegistros.agregarRegistro(), pnlBotones);
        generarBoton("Generar Reporte", e -> generarReportes(), pnlBotones);
        generarBoton("Configuración", e -> mostrarMenuConfiguracion(), pnlBotones);
        generarBoton("Salir", e -> salirDelPrograma(), pnlBotones);
    
        panel.add(pnlBotones, BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
    }
    
    public void crearTopBarVolverMenuPrincipal(String nombreMenu) {
        TopBar.crearTopBar(nombreMenu, e -> mostrarMenuPrincipal(), panel);
    }

    public void mostrarMenuCategorias() {
        panel.removeAll();
        panel.setLayout(new BorderLayout());
        crearTopBarVolverMenuPrincipal("Menú de Categorías");
    
        JPanel pnlBotones = new JPanel();
        pnlBotones.setLayout(new GridLayout(0, 2, 50, 50));
    
        generarBoton("Crear Categoría", e -> gestorCategorias.crearCategoria(), pnlBotones);
        generarBoton("Modificar Categoría", e -> gestorCategorias.modificarCategoria(), pnlBotones);
        generarBoton("Eliminar Categoría", e -> gestorCategorias.eliminarCategoria(), pnlBotones);
        generarBoton("Ver Categorías", e -> gestorCategorias.mostrarCategorias(), pnlBotones);
    
        panel.add(pnlBotones, BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
    }    

    public void mostrarMenuRegistros() {
        panel.removeAll();
        panel.setLayout(new BorderLayout());
        crearTopBarVolverMenuPrincipal("Menú de Registros");
    
        JPanel pnlBotones = new JPanel();
        pnlBotones.setLayout(new GridLayout(0, 2, 50, 50));
    
        generarBoton("Ver Todos los Registros", e -> gestorRegistros.mostrarTodosLosRegistros(), pnlBotones);
        generarBoton("Ver Ingresos", e -> gestorRegistros.mostrarRegistrosPorTipo("Ingreso"), pnlBotones);
        generarBoton("Ver Egresos", e -> gestorRegistros.mostrarRegistrosPorTipo("Egreso"), pnlBotones);
        generarBoton("Ver Transferencias", e -> gestorRegistros.mostrarRegistrosPorTipo("Transaccion"), pnlBotones);
        generarBoton("Ver Registros por Fecha", e -> gestorRegistros.mostrarRegistrosPorFecha(), pnlBotones);
    
        panel.add(pnlBotones, BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
    }

    public void mostrarMenuCuentas() {
        panel.removeAll();
        panel.setLayout(new BorderLayout());
        crearTopBarVolverMenuPrincipal("Menú de Cuentas");
    
        JPanel pnlBotones = new JPanel();
        pnlBotones.setLayout(new GridLayout(0, 2, 50, 50));
    
        generarBoton("Ver Cuentas", e -> gestorCuentas.mostrarCuentas(), pnlBotones);
        generarBoton("Agregar Cuenta", e -> gestorCuentas.agregarCuenta(), pnlBotones);
        generarBoton("Editar Cuenta", e -> gestorCuentas.editarCuenta(), pnlBotones);
        generarBoton("Eliminar Cuenta", e -> gestorCuentas.eliminarCuenta(), pnlBotones);
    
        panel.add(pnlBotones, BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
    }    

    public void mostrarMenuConfiguracion() {
        panel.removeAll();
        panel.setLayout(new BorderLayout());
        crearTopBarVolverMenuPrincipal("Menú de Configuración");
    
        JPanel pnlBotones = new JPanel();
        pnlBotones.setLayout(new GridLayout(0, 2, 50, 50));
    
        generarBoton("Configurar Moneda Predeterminada", e -> gestorMonedas.configurarMoneda(), pnlBotones);
        generarBoton("Configurar Formato de Fecha", e -> gestorFechas.configurarFormatoFecha(), pnlBotones);
    
        panel.add(pnlBotones, BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
    }    

    private void generarReportes() {
        // Lógica para generar reportes
    }


    private void salirDelPrograma() {
        ManejoArchivos.guardarDatos(registros, cuentas, categorias, configuracion);
        JOptionPane.showMessageDialog(null, "Saliendo de la aplicación. ¡Hasta luego!");
        ventana.dispose();
    }
}
