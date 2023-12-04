package facade;

import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.swing.*;
import java.awt.*;

import categoria.Categoria;
import cuentas.Cuenta;
import registros.*;
import facade.componentes.BotonCancelar;
import facade.componentes.TopBar;
import facade.estilos.Estilos;

public class GestorRegistros {
    private static final Cuenta cuentaSeleccionada = null;
    private GestorCuentas gestorCuentas;
    private GestorCategorias gestorCategorias;
    private List<Cuenta> cuentas;
    private List<Registro> registros;
    private List<Categoria> categorias;
    private JPanel panel;
    private ActionListener menuAVolver;
    private Configuracion configuracion;
    
    public GestorRegistros(GestorCategorias gestorCategorias, GestorCuentas gestorCuentas, List<Registro> registros, JPanel panel, ActionListener menuAVolver, Configuracion configuracion){
        this.gestorCuentas = gestorCuentas;
        this.gestorCategorias = gestorCategorias;
        this.panel = panel;
        this.menuAVolver = menuAVolver;
        this.registros = registros;
        this.cuentas = this.gestorCuentas.getCuentas();
        this.categorias = this.gestorCategorias.getCategorias();
        this.configuracion = configuracion;
    }

    private boolean existenRegistros() {
        if (registros.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay registros aún.", "Registros vacíos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        } else {
            return true;
        }
    }
    
    private String formatRegistroText(Registro registro) {
        return "<span style='font-family: Arial; font-size: 14pt; color: black;'>" + 
                "Fecha: <span style='font-weight: 400;'>" + registro.getFecha().obtenerFecha() + "</span><br>" +
                "Descripción: <span style='font-weight: 400;'>" + registro.getDescripcion()+ "</span><br>" +
                "Monto: <span style='font-weight: 400;'>" + registro.getMontoFormateado() + "</span></span>";
    }
    private String formatTitulo(String titulo) {
        return "<div style='text-align: center;font-family: Arial; font-size: 14pt; color: black;'>"+titulo+"</div>";
    }

    public JPanel formatearRegistro(Registro registro){
        String titulo;
        JPanel cartaRegistro = new JPanel(new FlowLayout());
        if(registro instanceof Ingreso) {
            Ingreso ingreso = (Ingreso) registro;
            Categoria categoria = ingreso.getCategoria();
            cartaRegistro.add(gestorCategorias.generarIcono(categoria));
            titulo = "Ingreso";
        }else if(registro instanceof Egreso) {
            Egreso egreso = (Egreso) registro;
            Categoria categoria = egreso.getCategoria();
            cartaRegistro.add(gestorCategorias.generarIcono(categoria));
            titulo = "Egreso";
        }else {
            titulo = "Transferencia";
        }
        JLabel textoRegistro = new JLabel("<html>" + formatTitulo(titulo) + "<br>" + "<div style='text-align: left;'>" + 
                formatRegistroText(registro) + "</div></html>");
        cartaRegistro.add(textoRegistro);
        return cartaRegistro;
    }

    public void mostrarTodosLosRegistros() {
        if (existenRegistros()) {
            panel.removeAll();
            panel.setLayout(new BorderLayout());
            
            // Cambia "Registros" por el nombre que desees mostrar en la barra superior
            TopBar.crearTopBar("Registros", menuAVolver, panel);
            
            JPanel pnlRegistros = new JPanel(new GridLayout(0, 1));
            
            for (Registro registro : registros) {
                pnlRegistros.add(formatearRegistro(registro));
            }
            
            panel.add(pnlRegistros);
            panel.revalidate();
            panel.repaint();
        }
    }

    private boolean existenRegistrosPorTipo(String tipo) {
        boolean existen = false;
        for (Registro registro : registros) {
            if (registro.getClass().getSimpleName().equals(tipo)) {
                existen = true;
                break;
            }
        }
        if (!existen) {
            JOptionPane.showMessageDialog(null, "No hay registros de tipo " + tipo + ".", "Registros vacíos", JOptionPane.INFORMATION_MESSAGE);
        }
        return existen;
    }
  
    public void mostrarRegistrosPorTipo(String tipo) {
        if (existenRegistrosPorTipo(tipo)) {
            panel.removeAll();
            panel.setLayout(new BorderLayout());
            
            TopBar.crearTopBar("Registros de tipo " + tipo, menuAVolver, panel);
            
            JPanel pnlRegistros = new JPanel(new GridLayout(0, 1));
            
            for (Registro registro : registros) {
                if (registro.getClass().getSimpleName().equals(tipo)) {
                    pnlRegistros.add(formatearRegistro(registro));
                }
            }
            
            panel.add(pnlRegistros);
            panel.revalidate();
            panel.repaint();
        }
    }    

     public void mostrarRegistrosPorFecha() {
        panel.removeAll();
        panel.setLayout(new BorderLayout());

        JPanel panelContenido = new JPanel(new BorderLayout());
        JPanel panelRegistros = new JPanel(new GridLayout(0, 1));
        JScrollPane scrollPane = new JScrollPane(panelRegistros);
        panelContenido.add(scrollPane, BorderLayout.CENTER);

        JPanel panelBusqueda = new JPanel(new GridLayout(1, 4));
        JTextField txtDia = new JTextField();
        JTextField txtMes = new JTextField();
        JTextField txtAnio = new JTextField();

        
        java.net.URL imgURL = getClass().getResource("./componentes/iconos/search.png");
        ImageIcon icono = new ImageIcon(imgURL);
        JButton btnBuscar = new JButton(icono);
        Estilos.estilizarBoton(btnBuscar);
        btnBuscar.addActionListener(e -> {
            panelRegistros.removeAll();
            String dia = txtDia.getText();
            String mes = txtMes.getText();
            String anio = txtAnio.getText();

            String fechaInput = dia + "/" + mes + "/" + anio;

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDateTime fechaBusqueda = LocalDateTime.parse(fechaInput, formatter);

                if (registros.isEmpty()) {
                    JLabel label = new JLabel("No hay registros aún.");
                    panelRegistros.add(label);
                    panelRegistros.revalidate();
                    panelRegistros.repaint();
                    return;
                }

                boolean registrosEncontrados = false;

                for (Registro registro : registros) {
                    LocalDateTime fechaRegistro = quitarTiempo(registro.getFecha().getFecha());
                    fechaBusqueda = quitarTiempo(fechaBusqueda);
                    if (fechaRegistro != null && fechaRegistro.compareTo(fechaBusqueda) == 0) {
                        panelRegistros.add(formatearRegistro(registro));
                        registrosEncontrados = true;
                    }
                }

                if (!registrosEncontrados) {
                    JLabel labelNoEncontrado = new JLabel("No se encontraron registros para la fecha " + fechaInput);
                    panelRegistros.add(labelNoEncontrado);
                }
                panelRegistros.revalidate();
                panelRegistros.repaint();
            } catch (DateTimeParseException ex) {
                JLabel labelError = new JLabel("Formato de fecha incorrecto. Por favor, usa el formato DD/MM/AAAA.");
                panelRegistros.add(labelError);
                panelRegistros.revalidate();
                panelRegistros.repaint();
            }
        });

        panelBusqueda.add(txtDia);
        panelBusqueda.add(txtMes);
        panelBusqueda.add(txtAnio);
        panelBusqueda.add(btnBuscar);

        panelContenido.add(panelBusqueda, BorderLayout.PAGE_START);
        TopBar.crearTopBar("Buscar Registros por Fecha", menuAVolver, panel);
        panel.add(panelContenido, BorderLayout.CENTER);

        panel.revalidate();
        panel.repaint();
    }
    
    public LocalDateTime quitarTiempo(LocalDateTime fecha) {
        if (fecha == null) {
            return null;
        }
        return fecha.toLocalDate().atStartOfDay();
    }
    
    public void agregarRegistro() {

        if(cuentas.isEmpty()){
            JOptionPane.showMessageDialog(null, "No existen cuentas. Por favor, primero agrega una cuenta.", "Cuentas no existentes", JOptionPane.WARNING_MESSAGE);
            return;
        }

        panel.removeAll();
        panel.setLayout(new BorderLayout());
    
        TopBar.crearTopBar("Elige el tipo de registro", e -> menuAVolver.actionPerformed(null), panel);
    
        JPanel panelTipoRegistro = new JPanel(new GridLayout(3, 1));
    
        JButton btnIngreso = new JButton("Agregar Ingreso");
        JButton btnEgreso = new JButton("Agregar Egreso");
        JButton btnTransferencia = new JButton("Agregar Transferencia");
        Estilos.estilizarBoton(btnIngreso);
        Estilos.estilizarBoton(btnEgreso);
        Estilos.estilizarBoton(btnTransferencia);
    
        btnIngreso.addActionListener(e -> {
            mostrarFormularioRegistro("Ingreso");
        });
    
        btnEgreso.addActionListener(e -> {
            mostrarFormularioRegistro("Egreso");
        });
    
        btnTransferencia.addActionListener(e -> {
            if(cuentas.size() < 2){
                JOptionPane.showMessageDialog(null, "No existen suficientes cuentas para hacer una transferencia.", "Cuentas insuficientes", JOptionPane.WARNING_MESSAGE);
            }else
                mostrarFormularioRegistro("Transferencia");
        });
    
        panelTipoRegistro.add(btnIngreso);
        panelTipoRegistro.add(btnEgreso);
        panelTipoRegistro.add(btnTransferencia);
    
        panel.add(panelTipoRegistro, BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
    }
    
    public JComboBox<String> crearListaCategorias(){
        JComboBox<String> listaCategorias = new JComboBox<>();

        if (categorias.isEmpty()) {
            listaCategorias.addItem("Sin categoría");
            listaCategorias.setEnabled(false);
        } else {
            for (Categoria categoria : categorias) {
                listaCategorias.addItem(categoria.getNombre());
            }
        }
        return listaCategorias;
    }

    public JComboBox<String> crearListaCuentas(){
        JComboBox<String> listaCuentas = new JComboBox<>();

        if (cuentas.isEmpty()) {
            listaCuentas.addItem("No hay cuentas disponibles");
            listaCuentas.setEnabled(false);
        } else {
            for (Cuenta cuenta : cuentas) {
                listaCuentas.addItem(cuenta.getNombre());
            }
        }
        return listaCuentas;
    }

    private void generarIngreso(String descripcion, double monto, Categoria categoriaSeleccionada, Cuenta cuentaSeleccionada){
        Ingreso ingreso = new Ingreso.IngresoBuilder()
            .withFecha(LocalDateTime.now())
            .withDescripcion(descripcion)
            .withMonto(monto)
            .withConfiguracion(this.configuracion)
            .withCategoria(categoriaSeleccionada)
            .withCuentaAsociada(cuentaSeleccionada)
            .build();
        registros.add(ingreso);
        cuentaSeleccionada.actualizarBalance(monto);
        menuAVolver.actionPerformed(null);
    }

    private void generarEgreso(String descripcion, double monto, Categoria categoriaSeleccionada, Cuenta cuentaSeleccionada){
        Egreso egreso = new Egreso.EgresoBuilder()
            .withFecha(LocalDateTime.now())
            .withDescripcion(descripcion)
            .withMonto(monto)
            .withConfiguracion(this.configuracion)
            .withCategoria(categoriaSeleccionada)
            .withCuentaAsociada(cuentaSeleccionada)
            .build();
        registros.add(egreso);
        cuentaSeleccionada.actualizarBalance(-monto);
        menuAVolver.actionPerformed(null);
    }
    private void generarTransferencia(String descripcion, double monto, JComboBox<String> listaCuentaOrigen, JComboBox<String> listaCuentaDestino){
        Cuenta cuentaOrigen = cuentas.isEmpty() ? null : cuentas.get(listaCuentaOrigen.getSelectedIndex());
        Cuenta cuentaDestino = cuentas.isEmpty() ? null : cuentas.get(listaCuentaDestino.getSelectedIndex());
        if (cuentaOrigen != cuentaDestino) {
            double saldoOrigen = cuentaOrigen.getSaldo();
            if (saldoOrigen >= monto) {
                Transaccion transaccion = new Transaccion.TransaccionBuilder()
                    .withFecha(LocalDateTime.now())
                    .withDescripcion(descripcion)
                    .withMonto(monto)
                    .withConfiguracion(this.configuracion)
                    .withCuentaOrigen(cuentaOrigen)
                    .withCuentaDestino(cuentaDestino)
                    .build();
                registros.add(transaccion);
                cuentaOrigen.actualizarBalance(-monto);
                cuentaDestino.actualizarBalance(monto);
                menuAVolver.actionPerformed(null);
            } else {
                JOptionPane.showMessageDialog(null, "Error: La cuenta de origen no tiene fondos suficientes para realizar la transferencia.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error: Seleccione distintas cuentas para hacer válida la transferencia.");
        }
    }

    public void mostrarFormularioRegistro(String tipoRegistro) {
        panel.removeAll();
        panel.setLayout(new BorderLayout());

        TopBar.crearTopBar("Agregar " + tipoRegistro, e -> agregarRegistro(), panel);

        JPanel panelFormulario = new JPanel(new GridLayout(5, 2));

        JTextField txtDescripcion = new JTextField();
        JTextField txtMonto = new JTextField();

        panelFormulario.add(new JLabel("Descripción:"));
        panelFormulario.add(txtDescripcion);
        panelFormulario.add(new JLabel("Monto:"));
        panelFormulario.add(txtMonto);

        JComboBox<String> listaCategorias = crearListaCategorias();
        JComboBox<String> listaCuentas = crearListaCuentas();
        JComboBox<String> listaCuentaOrigen = crearListaCuentas();
        JComboBox<String> listaCuentaDestino = crearListaCuentas();

        if (tipoRegistro.equals("Ingreso") || tipoRegistro.equals("Egreso")) {
            panelFormulario.add(new JLabel("Categoría:"));
            panelFormulario.add(listaCategorias);
            panelFormulario.add(new JLabel("Cuenta:"));
            panelFormulario.add(listaCuentas);
        } else if (tipoRegistro.equals("Transferencia")) {
            panelFormulario.add(new JLabel("Cuenta Origen:"));
            panelFormulario.add(listaCuentaOrigen);
            panelFormulario.add(new JLabel("Cuenta Destino:"));
            panelFormulario.add(listaCuentaDestino);
        }
        
        JButton btnAgregarRegistro = new JButton("Agregar");
        Estilos.estilizarBoton(btnAgregarRegistro);
        btnAgregarRegistro.addActionListener(e -> {
            String descripcion = txtDescripcion.getText();
            double monto = Double.parseDouble(txtMonto.getText());
    
            Categoria categoriaSeleccionada = categorias.isEmpty() ? null : categorias.get(listaCategorias.getSelectedIndex());
            Cuenta cuentaSeleccionada = cuentas.isEmpty() ? null : cuentas.get(listaCuentas.getSelectedIndex());
    
            if (tipoRegistro.equals("Ingreso")) {
                generarIngreso(descripcion, monto, categoriaSeleccionada, cuentaSeleccionada);
            } else if (tipoRegistro.equals("Egreso")) {
                generarEgreso(descripcion, monto, categoriaSeleccionada, cuentaSeleccionada);
            }  else if (tipoRegistro.equals("Transferencia")) {
                generarTransferencia(descripcion, monto, listaCuentaOrigen, listaCuentaDestino);
            }
        });
    
        panelFormulario.add(btnAgregarRegistro);
        panelFormulario.add(BotonCancelar.crearBoton(menuAVolver));
    
        panel.add(panelFormulario, BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
    }    
}
