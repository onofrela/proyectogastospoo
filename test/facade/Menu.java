package test.facade;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import categoria.Categoria;
import cuentas.Cuenta;
import datos.ManejoArchivos;
import registros.Registro;

public class Menu {

    public JButton btnTransacciones, btnCuentas, btnCategorias, btnAgregar, btnReportes, btnConfiguracion, btnSalir;
    public JLabel lblBalance;
    public JPanel panel;
    public JFrame ventana;

    private static List<Registro> registros = new ArrayList<>();
    private static List<Cuenta> cuentas = new ArrayList<>();
    private static List<Categoria> categorias = new ArrayList<>();

    private static GestorCuentas gestorCuentas;
    private static GestorCategorias gestorCategorias;
    private static GestorRegistros gestorRegistros;

    public Menu(JFrame ventana, JPanel panel){
        this.ventana = ventana;
        this.panel = panel;

        ManejoArchivos.cargarDatos(registros, cuentas, categorias);
        
        gestorCuentas = new GestorCuentas(cuentas);
        gestorCategorias = new GestorCategorias(categorias);
        gestorRegistros = new GestorRegistros(cuentas, registros, gestorCategorias, gestorCuentas);

    }
    
    public void mostrarMenuPrincipal() {
        panel.removeAll();
        panel.repaint();

        lblBalance = new JLabel("Balance: ");
        lblBalance.setBounds(20, 20, 200, 30);
        panel.add(lblBalance);
        Balance.mostrarBalances(cuentas);

        btnTransacciones = new JButton("Ver Registros");
        btnTransacciones.setBounds(20, 60, 200, 30);
        panel.add(btnTransacciones);

        btnCuentas = new JButton("Gestionar Cuentas");
        btnCuentas.setBounds(20, 100, 200, 30);
        panel.add(btnCuentas);

        btnCategorias = new JButton("Gestionar Categorías");
        btnCategorias.setBounds(20, 140, 200, 30);
        panel.add(btnCategorias);

        btnAgregar = new JButton("Agregar Registro");
        btnAgregar.setBounds(20, 180, 200, 30);
        panel.add(btnAgregar);

        btnReportes = new JButton("Generar Reportes");
        btnReportes.setBounds(20, 220, 200, 30);
        panel.add(btnReportes);

        btnConfiguracion = new JButton("Configuración");
        btnConfiguracion.setBounds(230, 180, 150, 30);
        panel.add(btnConfiguracion);

        btnSalir = new JButton("Salir");
        btnSalir.setBounds(230, 220, 150, 30);
        panel.add(btnSalir);

        btnTransacciones.addActionListener(e -> mostrarMenuRegistros());
        btnCuentas.addActionListener(e -> mostrarMenuCuentas());
        btnCategorias.addActionListener(e -> mostrarMenuCategorias());
        btnAgregar.addActionListener(e -> gestorRegistros.agregarRegistro());
        btnReportes.addActionListener(e -> generarReportes());
        btnConfiguracion.addActionListener(e -> mostrarMenuConfiguracion());
        btnSalir.addActionListener(e -> salirDelPrograma());

        panel.revalidate();
    }

    public JButton crearBotonVolverMenuPrincipal() {
        JButton btnVolver = new JButton("Volver al Menú Principal");
        btnVolver.setBounds(230, 20, 150, 30);
        btnVolver.addActionListener(e -> volverAlMenuPrincipal());
        return btnVolver;
    }
        
    public void volverAlMenuPrincipal() {
        mostrarMenuPrincipal(); // Simplemente mostrar el menú principal
    }

    public void mostrarMenuCategorias() {
        panel.removeAll();
        panel.repaint();
        panel.add(crearBotonVolverMenuPrincipal());

        lblBalance.setText("Menú de Categorías");
        lblBalance.setBounds(20, 20, 200, 30);
        panel.add(lblBalance);

        JButton btnCrearCategoria = new JButton("Crear Categoría");
        btnCrearCategoria.setBounds(20, 60, 200, 30);
        panel.add(btnCrearCategoria);

        JButton btnModificarCategoria = new JButton("Modificar Categoría");
        btnModificarCategoria.setBounds(20, 100, 200, 30);
        panel.add(btnModificarCategoria);

        JButton btnEliminarCategoria = new JButton("Eliminar Categoría");
        btnEliminarCategoria.setBounds(20, 140, 200, 30);
        panel.add(btnEliminarCategoria);

        JButton btnVerCategorias = new JButton("Ver Categorías");
        btnVerCategorias.setBounds(20, 180, 200, 30);
        panel.add(btnVerCategorias);

        btnCrearCategoria.addActionListener(e -> gestorCategorias.crearCategoria());
        btnModificarCategoria.addActionListener(e -> gestorCategorias.modificarCategoria());
        btnEliminarCategoria.addActionListener(e -> gestorCategorias.eliminarCategoria());
        btnVerCategorias.addActionListener(e -> gestorCategorias.listarCategorias());

        panel.revalidate();
    }

    public void mostrarMenuRegistros() {
        panel.removeAll();
        panel.repaint();
        panel.add(crearBotonVolverMenuPrincipal());

        lblBalance.setText("Menú de Registro");
        lblBalance.setBounds(20, 20, 200, 30);
        panel.add(lblBalance);

        JButton btnVerRegistros = new JButton("Ver Todos los Registros");
        btnVerRegistros.setBounds(20, 60, 200, 30);
        panel.add(btnVerRegistros);

        JButton btnVerIngresos = new JButton("Ver Ingresos");
        btnVerIngresos.setBounds(20, 100, 200, 30);
        panel.add(btnVerIngresos);

        JButton btnVerEgresos = new JButton("Ver Egresos");
        btnVerEgresos.setBounds(20, 140, 200, 30);
        panel.add(btnVerEgresos);

        JButton btnVerTransferencias = new JButton("Ver Transferencias");
        btnVerTransferencias.setBounds(20, 180, 200, 30);
        panel.add(btnVerTransferencias);

        JButton btnVerPorFecha = new JButton("Ver Registros por Fecha");
        btnVerPorFecha.setBounds(20, 220, 200, 30);
        panel.add(btnVerPorFecha);

        btnVerRegistros.addActionListener(e -> gestorRegistros.mostrarTodosLosRegistros());
        btnVerIngresos.addActionListener(e -> gestorRegistros.mostrarRegistrosPorTipo("Ingreso"));
        btnVerEgresos.addActionListener(e -> gestorRegistros.mostrarRegistrosPorTipo("Egreso"));
        btnVerTransferencias.addActionListener(e -> gestorRegistros.mostrarRegistrosPorTipo("Transaccion"));
        btnVerPorFecha.addActionListener(e -> gestorRegistros.mostrarRegistrosPorFecha());

        panel.revalidate();
    }

    public void mostrarMenuCuentas() {
        panel.removeAll();
        panel.repaint();
        panel.add(crearBotonVolverMenuPrincipal());

        lblBalance.setText("Menú de Gestionar Cuentas");
        lblBalance.setBounds(20, 20, 200, 30);
        panel.add(lblBalance);

        JButton btnVerCuentas = new JButton("Ver Cuentas");
        btnVerCuentas.setBounds(20, 60, 200, 30);
        panel.add(btnVerCuentas);

        JButton btnAgregarCuenta = new JButton("Agregar Cuenta");
        btnAgregarCuenta.setBounds(20, 100, 200, 30);
        panel.add(btnAgregarCuenta);

        JButton btnEditarCuenta = new JButton("Editar Cuenta");
        btnEditarCuenta.setBounds(20, 140, 200, 30);
        panel.add(btnEditarCuenta);

        JButton btnEliminarCuenta = new JButton("Eliminar Cuenta");
        btnEliminarCuenta.setBounds(20, 180, 200, 30);
        panel.add(btnEliminarCuenta);

        btnVerCuentas.addActionListener(e -> gestorCuentas.mostrarCuentas());
        btnAgregarCuenta.addActionListener(e -> gestorCuentas.agregarCuenta());
        btnEditarCuenta.addActionListener(e -> gestorCuentas.editarCuenta());
        btnEliminarCuenta.addActionListener(e -> gestorCuentas.eliminarCuenta());

        panel.revalidate();
    }

    public void mostrarMenuConfiguracion() {
        panel.removeAll();
        panel.repaint();
        panel.add(crearBotonVolverMenuPrincipal());

        lblBalance.setText("Menú de Configuración");
        lblBalance.setBounds(20, 20, 200, 30);
        panel.add(lblBalance);

        JButton btnMoneda = new JButton("Configurar Moneda Predeterminada");
        btnMoneda.setBounds(20, 60, 300, 30);
        panel.add(btnMoneda);

        JButton btnFecha = new JButton("Configurar Formato de Fecha");
        btnFecha.setBounds(20, 100, 300, 30);
        panel.add(btnFecha);

        btnMoneda.addActionListener(e -> configurarMoneda());
        btnFecha.addActionListener(e -> configurarFormatoFecha());

        panel.revalidate();
    }

    private void generarReportes() {
        // Lógica para generar reportes
    }

    private void configurarMoneda() {
        // Lógica para configurar la moneda
    }

    private void configurarFormatoFecha() {
        // Lógica para configurar el formato de la fecha
    }

    private void salirDelPrograma() {
        // Lógica para salir del programa
        ManejoArchivos.guardarDatos(registros, cuentas, categorias);
        JOptionPane.showMessageDialog(null, "Saliendo de la aplicación. ¡Hasta luego!");
        ventana.dispose(); // Cierra la ventana
        // Aquí podrías agregar la lógica para guardar los datos antes de salir
    }
}
