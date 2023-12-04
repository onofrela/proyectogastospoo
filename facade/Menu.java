package facade;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import categoria.Categoria;
import cuentas.Cuenta;
import datos.ManejoArchivos;
import registros.Registro;
import facade.componentes.TopBar;
import facade.estilos.Estilos;

public class Menu {
    public JLabel lblBalance;
    public JPanel panel;
    public JFrame ventana;

    private List<Registro> registros = new ArrayList<>();
    private List<Cuenta> cuentas = new ArrayList<>();
    private List<Categoria> categorias = new ArrayList<>();
    private Configuracion configuracion;

    private static GestorCuentas gestorCuentas;
    private static GestorCategorias gestorCategorias;
    private static GestorRegistros gestorRegistros;
    private static GestorFechas gestorFechas;
    private static GestorMonedas gestorMonedas;


    private Balance balance;

    public Menu(JFrame ventana, JPanel panel, List<Registro> registros, List<Cuenta> cuentas, List<Categoria> categorias, Configuracion configuracion){
        this.ventana = ventana;
        this.panel = panel;
        this.registros = registros;
        this.cuentas = cuentas;
        this.categorias = categorias;
        this.configuracion = configuracion;
        this.balance = new Balance(this.categorias, this.registros, this.cuentas, this.configuracion);
        gestorCuentas = new GestorCuentas(this.cuentas, e -> mostrarMenuCuentas(), this.panel, this.configuracion);
        gestorCategorias = new GestorCategorias(this.categorias, e -> mostrarMenuCategorias(), this.panel);
        gestorRegistros = new GestorRegistros(gestorCategorias, gestorCuentas, this.registros, this.panel, e -> mostrarMenuRegistros(), this.configuracion);
        gestorMonedas = new GestorMonedas(this.panel, e -> mostrarMenuConfiguracion(), this.configuracion);
        gestorFechas = new GestorFechas(this.panel, e -> mostrarMenuConfiguracion(), this.configuracion);
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

    public String formatearMonto(double monto) {
        return this.configuracion.getFormatoMonto().getSimboloMoneda() + String.format("%.2f", monto) + " " + this.configuracion.getFormatoMonto().getMoneda();
    }

    private JPanel generarGraficaIngresosPorCategoria() {
        JPanel pnlGraficaIngresos = new JPanel();
        pnlGraficaIngresos.setLayout(new GridLayout(0, 1, 50, 50));
    
        Map<Categoria, Double> mapaBalance = balance.darMapaBalanceIngresoPorCategoria();
    
        JPanel chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                double total = mapaBalance.values().stream().mapToDouble(Double::doubleValue).sum();
                double startAngle = 0.0;
                int x = 50;
                int y = 50;
                int width = 200;
                int height = 200;
    
                int legendX = 300;
                int legendY = 50;
                int legendHeight = 20;
    
                g.setFont(new Font("Arial", Font.PLAIN, 12));
                for (Map.Entry<Categoria, Double> categoria : mapaBalance.entrySet()) {
                    double monto = categoria.getValue();
                    int arcAngle = (int) Math.round(monto / total * 360);
    
                    g.setColor(categoria.getKey().getColor());
                    g.fillArc(x, y, width, height, (int) startAngle, arcAngle);
    
                    g.fillRect(legendX, legendY, 20, legendHeight);
                    g.drawString(categoria.getKey().getNombre() + " - " + formatearMonto(monto), legendX + 30, legendY + legendHeight - 5);
                    legendY += 25;
    
                    startAngle += arcAngle;
                }
                
                g.setFont(new Font("Arial", Font.BOLD, 18));
                g.setColor(Color.BLACK);
                FontMetrics metrics = g.getFontMetrics();
                int titleWidth = metrics.stringWidth("Ingresos por categoría");
                int titleX = (getWidth() - titleWidth) / 2;
                int titleY = 30;
                g.drawString("Ingresos por categoría", titleX, titleY);
            }
        };
    
        pnlGraficaIngresos.add(chartPanel);
        return pnlGraficaIngresos;
    }

    private JPanel generarGraficaEgresosPorCategoria() {
        JPanel pnlGraficasEgresos = new JPanel();
        pnlGraficasEgresos.setLayout(new GridLayout(0, 1, 50, 50));
        Map<Categoria, Double> mapaBalance = balance.darMapaBalanceEgresoPorCategoria();
    
        JPanel chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                double total = mapaBalance.values().stream().mapToDouble(Double::doubleValue).sum();
                double startAngle = 0.0;
                int x = 50;
                int y = 50;
                int width = 200;
                int height = 200;
    
                int legendX = 300;
                int legendY = 50;
                int legendHeight = 20;
    
                g.setFont(new Font("Arial", Font.PLAIN, 12));
                for (Map.Entry<Categoria, Double> categoria : mapaBalance.entrySet()) {
                    double monto = categoria.getValue();
                    int arcAngle = (int) Math.round(monto / total * 360);
    
                    g.setColor(categoria.getKey().getColor());
                    g.fillArc(x, y, width, height, (int) startAngle, arcAngle);
    
                    g.fillRect(legendX, legendY, 20, legendHeight);
                    g.drawString(categoria.getKey().getNombre() + " - " + formatearMonto(monto), legendX + 30, legendY + legendHeight - 5);
                    
                    legendY += 25;
    
                    startAngle += arcAngle;
                }
                
                g.setFont(new Font("Arial", Font.BOLD, 18));
                g.setColor(Color.BLACK);
                FontMetrics metrics = g.getFontMetrics();
                int titleWidth = metrics.stringWidth("Egresos por categoría");
                int titleX = (getWidth() - titleWidth) / 2;
                int titleY = 30;
                g.drawString("Egresos por categoría", titleX, titleY);
            }
        };
    
        pnlGraficasEgresos.add(chartPanel);
        return pnlGraficasEgresos;
    }
    
    private void generarReportes() {
        if(gestorRegistros.existenRegistros()){ 
            panel.removeAll();
            panel.setLayout(new BorderLayout());
            crearTopBarVolverMenuPrincipal("Reporte");

            JPanel pnlGraficas = new JPanel();
            pnlGraficas.setLayout(new GridLayout(1, 2, 50, 50));

            pnlGraficas.add(generarGraficaIngresosPorCategoria());
            pnlGraficas.add(generarGraficaEgresosPorCategoria());

            panel.add(pnlGraficas, BorderLayout.CENTER);
            panel.revalidate();
            panel.repaint();
        }
    }
        
    private void salirDelPrograma() {
        ManejoArchivos.guardarDatos(this.registros, this.cuentas, this.categorias, this.configuracion);
        JOptionPane.showMessageDialog(null, "Saliendo de la aplicación. ¡Hasta luego!", "Cerrando aplicación", JOptionPane.PLAIN_MESSAGE);
        ventana.dispose();
    }
}
