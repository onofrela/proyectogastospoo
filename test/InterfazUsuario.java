package test;

import javax.swing.*;

import categoria.Categoria;
import cuentas.Cuenta;
import datos.ManejoArchivos;
import facade.GestorCategorias;
import facade.GestorCuentas;
import facade.GestorRegistros;
import registros.Registro;
import test.facade.Balance;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

// Importa las librerías necesarias

public class InterfazUsuario extends JFrame {

    // Declaración de componentes necesarios
    private JPanel panelPrincipal;
    private JButton botonMenuPrincipal;

    private List<Registro> registros = new ArrayList<>();
    private List<Cuenta> cuentas = new ArrayList<>();
    private List<Categoria> categorias = new ArrayList<>();

    private static GestorCuentas gestorCuentas;
    private static GestorCategorias gestorCategorias;
    private static GestorRegistros gestorRegistros;

    private JTextArea areaBalance;

    public InterfazUsuario() {
        super("Mi Aplicación Financiera"); // Título de la ventana

        // Configuración básica de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400); // Tamaño fijo de la ventana (ancho x alto)
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setLayout(new BorderLayout()); // Usar BorderLayout para el diseño

        // Creación del panel principal y del botón del menú principal

        ManejoArchivos.cargarDatos(registros, cuentas, categorias);
        
        gestorCuentas = new GestorCuentas(cuentas);
        gestorCategorias = new GestorCategorias(categorias);
        gestorRegistros = new GestorRegistros(cuentas, registros, gestorCategorias, gestorCuentas);

        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new FlowLayout());

        botonMenuPrincipal = new JButton("Menú Principal");
        botonMenuPrincipal.addActionListener(e -> mostrarMenuPrincipal());

        panelPrincipal.add(botonMenuPrincipal);

        areaBalance = new JTextArea(10, 20); // Crea un área de texto (10 filas x 20 columnas)
        areaBalance.setEditable(false); // Hace que el área de texto no sea editable
        JScrollPane scrollPane = new JScrollPane(areaBalance); // Agrega un scroll al área de texto
        panelPrincipal.add(scrollPane); // Agrega el área de texto al panel principal

        add(panelPrincipal, BorderLayout.CENTER);
    }

    private void mostrarMenuPrincipal() {
        panelPrincipal.removeAll(); // Limpiar el panel principal
    
        JButton botonVerTransacciones = new JButton("Ver Transacciones");
        botonVerTransacciones.addActionListener(e -> mostrarMenuTransacciones());
    
        JButton botonGestionarCuentas = new JButton("Gestionar Cuentas");
        botonGestionarCuentas.addActionListener(e -> mostrarMenuCuentas());
    
        JButton botonGestionarCategorias = new JButton("Gestionar Categorías");
        botonGestionarCategorias.addActionListener(e -> mostrarMenuCategorias());
    
        JButton botonGenerarReportes = new JButton("Generar Reportes");
        //botonGenerarReportes.addActionListener(e -> generarReportes());
    
        JButton botonConfiguracion = new JButton("Configuración");
        botonConfiguracion.addActionListener(e -> mostrarMenuConfiguracion());
    
        JButton botonSalir = new JButton("Salir");
        botonSalir.addActionListener(e -> cerrarAplicacion()); // Lógica para salir de la aplicación
    
        panelPrincipal.add(botonVerTransacciones);
        panelPrincipal.add(botonGestionarCuentas);
        panelPrincipal.add(botonGestionarCategorias);
        panelPrincipal.add(botonGenerarReportes);
        panelPrincipal.add(botonConfiguracion);
        panelPrincipal.add(botonSalir);
        
        Balance.mostrarBalances(cuentas, areaBalance);

        revalidate(); // Revalidar el panel para mostrar los cambios
        repaint(); // Repintar la ventana
    }

    public void cerrarAplicacion() {
        guardarDatos(); // Llama al método para guardar datos antes de cerrar la aplicación
        mostrarMensaje("Saliendo de la aplicación. ¡Hasta luego!");
        System.exit(0);
    }
    
    private void guardarDatos() {
        ManejoArchivos.guardarDatos(registros, cuentas, categorias); // Llama al método de guardado de datos
    }

    private void mostrarMenuTransacciones() {
        panelPrincipal.removeAll(); // Limpiar el panel principal
    
        JButton botonVerTodos = new JButton("Ver Todos los Registros");
        botonVerTodos.addActionListener(e -> gestorRegistros.mostrarTodosLosRegistros()); // Lógica para mostrar todos los registros
    
        JButton botonVerIngresos = new JButton("Ver Ingresos");
        botonVerIngresos.addActionListener(e -> gestorRegistros.mostrarRegistrosPorTipo("Ingreso")); // Lógica para mostrar ingresos
    
        JButton botonVerEgresos = new JButton("Ver Egresos");
        botonVerEgresos.addActionListener(e -> gestorRegistros.mostrarRegistrosPorTipo("Egreso")); // Lógica para mostrar egresos
    
        JButton botonVerTransferencias = new JButton("Ver Transferencias");
        botonVerTransferencias.addActionListener(e -> gestorRegistros.mostrarRegistrosPorTipo("Transacción")); // Lógica para mostrar transferencias
    
        JButton botonVerPorFecha = new JButton("Ver Registros por Fecha");
        botonVerPorFecha.addActionListener(e -> gestorRegistros.mostrarRegistrosPorFecha()); // Lógica para mostrar por fecha
    
        JButton botonVolver = new JButton("Volver al Menú Principal");
        botonVolver.addActionListener(e -> mostrarMenuPrincipal()); // Volver al menú principal
    
        panelPrincipal.add(botonVerTodos);
        panelPrincipal.add(botonVerIngresos);
        panelPrincipal.add(botonVerEgresos);
        panelPrincipal.add(botonVerTransferencias);
        panelPrincipal.add(botonVerPorFecha);
        panelPrincipal.add(botonVolver);
    
        revalidate(); // Revalidar el panel para mostrar los cambios
        repaint(); // Repintar la ventana
    }    

    private void mostrarMenuCuentas() {
        panelPrincipal.removeAll(); // Limpiar el panel principal
    
        JButton botonVerCuentas = new JButton("Ver Cuentas");
        botonVerCuentas.addActionListener(e -> gestorCuentas.mostrarCuentas()); // Lógica para mostrar las cuentas
    
        JButton botonAgregarCuenta = new JButton("Agregar Cuenta");
        botonAgregarCuenta.addActionListener(e -> gestorCuentas.agregarCuenta()); // Lógica para agregar cuenta
    
        JButton botonEditarCuenta = new JButton("Editar Cuenta"); // Agrega lógica para editar cuenta si es necesario
        // botonEditarCuenta.addActionListener(e -> /* Lógica para editar cuenta */);
    
        JButton botonEliminarCuenta = new JButton("Eliminar Cuenta"); // Agrega lógica para eliminar cuenta si es necesario
        // botonEliminarCuenta.addActionListener(e -> /* Lógica para eliminar cuenta */);
    
        JButton botonVolver = new JButton("Volver al Menú Principal");
        botonVolver.addActionListener(e -> mostrarMenuPrincipal()); // Volver al menú principal
    
        panelPrincipal.add(botonVerCuentas);
        panelPrincipal.add(botonAgregarCuenta);
        // Agrega botones para editar y eliminar cuentas si es necesario
        panelPrincipal.add(botonVolver);
    
        revalidate(); // Revalidar el panel para mostrar los cambios
        repaint(); // Repintar la ventana
    }    

    private void mostrarMenuCategorias() {
        panelPrincipal.removeAll(); // Limpiar el panel principal
    
        JButton botonCrearCategoria = new JButton("Crear Categoría");
        botonCrearCategoria.addActionListener(e -> gestorCategorias.crearCategoria()); // Lógica para crear categoría
    
        JButton botonModificarCategoria = new JButton("Modificar Categoría");
        botonModificarCategoria.addActionListener(e -> gestorCategorias.modificarCategoria()); // Lógica para modificar categoría
    
        JButton botonEliminarCategoria = new JButton("Eliminar Categoría");
        botonEliminarCategoria.addActionListener(e -> gestorCategorias.eliminarCategoria()); // Lógica para eliminar categoría
    
        JButton botonVerCategorias = new JButton("Ver Categorías");
        botonVerCategorias.addActionListener(e -> {
            if (!categorias.isEmpty())
                gestorCategorias.enlistarCategorias();
            else
                mostrarMensaje("Sin categorías");
        }); // Lógica para ver categorías
    
        JButton botonVolver = new JButton("Volver al Menú Principal");
        botonVolver.addActionListener(e -> mostrarMenuPrincipal()); // Volver al menú principal
    
        panelPrincipal.add(botonCrearCategoria);
        panelPrincipal.add(botonModificarCategoria);
        panelPrincipal.add(botonEliminarCategoria);
        panelPrincipal.add(botonVerCategorias);
        panelPrincipal.add(botonVolver);
    
        revalidate(); // Revalidar el panel para mostrar los cambios
        repaint(); // Repintar la ventana
    }
    
    // Método para mostrar mensajes en la interfaz gráfica
    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }    

    private void mostrarMenuConfiguracion() {
        panelPrincipal.removeAll(); // Limpiar el panel principal
    
        JButton botonConfigurarMoneda = new JButton("Configurar Moneda Predeterminada");
        botonConfigurarMoneda.addActionListener(e -> {
            // Lógica para configurar moneda
            mostrarMensaje("Lógica para configurar moneda aquí");
        });
    
        JButton botonConfigurarFecha = new JButton("Configurar Formato de Fecha");
        botonConfigurarFecha.addActionListener(e -> {
            // Lógica para configurar formato de fecha
            mostrarMensaje("Lógica para configurar formato de fecha aquí");
        });
    
        JButton botonVolver = new JButton("Volver al Menú Principal");
        botonVolver.addActionListener(e -> mostrarMenuPrincipal()); // Volver al menú principal
    
        panelPrincipal.add(botonConfigurarMoneda);
        panelPrincipal.add(botonConfigurarFecha);
        panelPrincipal.add(botonVolver);
    
        revalidate(); // Revalidar el panel para mostrar los cambios
        repaint(); // Repintar la ventana
    }
    
    // Implementa métodos para los demás menús de acuerdo a la estructura del código original

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfazUsuario ventana = new InterfazUsuario();
            ventana.setVisible(true);
        });
    }
}
