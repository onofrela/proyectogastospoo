package facade;

import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.List;

import javax.swing.*;
import java.awt.*;

import categoria.Categoria;
import facade.componentes.TopBar;

public class GestorCategorias {
    private List<Categoria> categorias;
    private ActionListener menuAVolver;
    private JPanel panel;
    private Categoria categoriaSeleccionada;
    public Categoria categoriaNula;
    private ImageIcon iconoSeleccionado;
    private Color colorSeleccionado;

    public GestorCategorias(List<Categoria> categorias, ActionListener menuAVolver, JPanel panel) {
        this.categorias = categorias;
        this.menuAVolver = menuAVolver;
        this.panel = panel;
        java.net.URL imgURL = getClass().getResource("/categoria/iconos/unknown.png");
        ImageIcon icono = new ImageIcon(imgURL);

        this.categoriaNula = new Categoria("Sin Categoria", icono, Color.WHITE);
        this.colorSeleccionado = Color.RED;
        this.iconoSeleccionado = new ImageIcon(getClass().getResource("/categoria/iconos/box.png"));
    }

    public Categoria sinCategoria() {
        return categoriaNula;
    }

    public List<Categoria> getCategorias(){
        return this.categorias;
    }
    
    public Color elegirColor() {
        Color[] coloresPermitidos = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE}; // Lista de colores permitidos
    
        JPanel pnlColores = new JPanel(new GridLayout(1, coloresPermitidos.length));
        JButton[] btnColores = new JButton[coloresPermitidos.length];
    
        // Crear botones para cada color permitido
        for (int i = 0; i < coloresPermitidos.length; i++) {
            btnColores[i] = new JButton();
            btnColores[i].setBackground(coloresPermitidos[i]);
            btnColores[i].setPreferredSize(new Dimension(30, 30));
    
            // Agregar acción al botón para seleccionar el color
            btnColores[i].addActionListener(e -> {
                JButton btnColorSeleccionado = (JButton) e.getSource();
                this.colorSeleccionado = btnColorSeleccionado.getBackground();
            });
    
            pnlColores.add(btnColores[i]);
        }
    
        int opcion = JOptionPane.showOptionDialog(null, pnlColores, "Seleccionar color", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
        if (opcion != JOptionPane.CLOSED_OPTION) {
            return coloresPermitidos[opcion];
        }
        return null; // Si se cierra la ventana sin selección
    }

    public String[] obtenerNombresIconos() {
        String directorioIconos = "/categoria/iconos";
    
        URL urlDirectorio = getClass().getResource(directorioIconos);
        File folder = new File(urlDirectorio.getFile());
        String[] nombresIconos = folder.list();
    
        return nombresIconos;
    }

    public void elegirIcono() {
        String[] nombresIconos = obtenerNombresIconos();
    
        if (nombresIconos != null && nombresIconos.length > 0) {
            JPanel pnlIconos = new JPanel(new GridLayout(0, 4));
    
            for (String nombreIcono : nombresIconos) {
                ImageIcon icono = new ImageIcon(getClass().getResource("/categoria/iconos/" + nombreIcono));
                JButton btnIcono = new JButton(icono);
                btnIcono.setPreferredSize(new Dimension(50, 50));
    
                // Acción al hacer clic en el botón de ícono
                btnIcono.addActionListener(e -> {
                    JButton btnIconoSeleccionado = (JButton) e.getSource();
                    // Actualizar el ícono seleccionado en el GestorCategorias
                    this.iconoSeleccionado = icono; // o el nombre del ícono seleccionado
                });
    
                pnlIconos.add(btnIcono);
            }
    
            JScrollPane scrollPane = new JScrollPane(pnlIconos);
            scrollPane.setPreferredSize(new Dimension(400, 300));
    
            JOptionPane.showMessageDialog(null, scrollPane, "Seleccionar ícono", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No hay íconos disponibles", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }    
    
    public void crearCategoria() {
        panel.removeAll();
        panel.setLayout(new BorderLayout());
        TopBar.crearTopBar("Crear nueva categoría", e -> menuAVolver.actionPerformed(null), panel);
    
        JPanel pnlCategoria = new JPanel(new GridLayout(3, 2));
    
        JLabel lblNombreCategoria = new JLabel("Nombre de la categoría:");
        JTextField txtNombreCategoria = new JTextField();
    
        JLabel lblIcono = new JLabel("Icono:");
        JButton btnSeleccionarIcono = new JButton("Seleccionar Icono");
    
        JLabel lblColor = new JLabel("Color:");
        JButton btnSeleccionarColor = new JButton("Seleccionar Color");
    
        pnlCategoria.add(lblNombreCategoria);
        pnlCategoria.add(txtNombreCategoria);
        pnlCategoria.add(lblIcono);
        pnlCategoria.add(btnSeleccionarIcono);
        pnlCategoria.add(lblColor);
        pnlCategoria.add(btnSeleccionarColor);
    
        // Acción al hacer clic en el botón para seleccionar color
        btnSeleccionarColor.addActionListener(e -> {
            Color colorSeleccionado = elegirColor(); // Método para mostrar colores y seleccionar uno
            if (colorSeleccionado != null) {
                // Hacer algo con el color seleccionado, como asignarlo a un componente o almacenarlo
                // txtColor.setText(colorSeleccionado.toString());
            }
        });
    
        // Acción al hacer clic en el botón para seleccionar ícono
        btnSeleccionarIcono.addActionListener(e -> {
            elegirIcono(); // Método para mostrar los íconos y permitir la selección
            // Dentro del método mostrarIconos, puedes definir cómo manejar la selección del ícono
        });
    
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(e -> {
            // Obtener los valores ingresados por el usuario
            String nuevoNombre = txtNombreCategoria.getText();
            // Obtener los valores de icono y color seleccionados, si los hay
    
            // Verificar si ya existe una categoría con ese nombre
            if (existeCategoria(nuevoNombre)) {
                JOptionPane.showMessageDialog(null, "¡La categoría ya existe!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            // Crear una nueva categoría y agregarla a la lista
            Categoria nuevaCategoria = new Categoria(nuevoNombre, iconoSeleccionado, colorSeleccionado);
            categorias.add(nuevaCategoria);
    
            JOptionPane.showMessageDialog(null, "Categoría creada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    
            // Volver al menú principal
            menuAVolver.actionPerformed(null);
        });
    
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> menuAVolver.actionPerformed(null));
    
        JPanel pnlBotones = new JPanel(new GridLayout(1, 2));
        pnlBotones.add(btnAgregar);
        pnlBotones.add(btnCancelar);
    
        panel.add(pnlCategoria, BorderLayout.CENTER);
        panel.add(pnlBotones, BorderLayout.SOUTH);
        panel.revalidate();
        panel.repaint();
    }    

    private boolean existeCategoria(String nombre) {
        for (Categoria categoria : categorias) {
            if (categoria.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }    

    private boolean existenCategorias() {
        if (categorias.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No existen categorías. Por favor, primero agrega una categoría.", "Categorías no existentes", JOptionPane.WARNING_MESSAGE);
            return false;
        } else {
            return true;
        }
    }

    public void modificarCategoria() {
        if (existenCategorias()) {
            elegirCategoria(e -> {
                JButton button = (JButton) e.getSource();
                categoriaSeleccionada = (Categoria) button.getClientProperty("categoria");
                editarDatosCategoria();
            });
        }
    }
    
    public void editarDatosCategoria() {
        panel.removeAll();
        panel.setLayout(new BorderLayout());
        TopBar.crearTopBar("Modificar categoría " + categoriaSeleccionada.getNombre(), e -> menuAVolver.actionPerformed(null), panel);
    
        JPanel pnlCategoria = new JPanel(new GridLayout(3, 2));
    
        JLabel lblNombreCategoria = new JLabel("Nombre de la categoría:");
        JLabel lblIcono = new JLabel("Icono:");
        JLabel lblColor = new JLabel("Color:");
    
        JTextField txtNombreCategoria = new JTextField(categoriaSeleccionada.getNombre());
        JButton btnSeleccionarIcono = new JButton("Seleccionar Icono");
        JButton btnSeleccionarColor = new JButton("Seleccionar Color");
    
        pnlCategoria.add(lblNombreCategoria);
        pnlCategoria.add(txtNombreCategoria);
        pnlCategoria.add(lblIcono);
        pnlCategoria.add(btnSeleccionarIcono);
        pnlCategoria.add(lblColor);
        pnlCategoria.add(btnSeleccionarColor);
    
        // Acción al hacer clic en el botón para seleccionar color
        btnSeleccionarColor.addActionListener(e -> {
            Color colorSeleccionado = elegirColor(); // Método para mostrar colores y seleccionar uno
            if (colorSeleccionado != null) {
                // Hacer algo con el color seleccionado, como asignarlo al componente correspondiente
                // txtColor.setText(colorSeleccionado.toString());
            }
        });
    
        // Acción al hacer clic en el botón para seleccionar ícono
        btnSeleccionarIcono.addActionListener(e -> {
            elegirIcono(); // Método para mostrar íconos y seleccionar uno
            if (iconoSeleccionado != null) {
                // Hacer algo con el ícono seleccionado, como asignarlo al componente correspondiente
                // txtIcono.setText(iconoSeleccionado);
            }
        });
    
        JButton btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(e -> {
            // Obtener los nuevos valores y actualizar la categoría
            String nuevoNombre = txtNombreCategoria.getText();
            // Obtener los valores de icono y color seleccionados, si los hay
    
            // Verificar si ya existe una categoría con ese nombre
            if (!nuevoNombre.equals(categoriaSeleccionada.getNombre()) && existeCategoria(nuevoNombre)) {
                JOptionPane.showMessageDialog(null, "¡Ya existe una categoría con ese nombre!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            // Actualizar los datos de la categoría seleccionada
            categoriaSeleccionada.setNombre(nuevoNombre);
            categoriaSeleccionada.setIcono(iconoSeleccionado);
            categoriaSeleccionada.setColor(colorSeleccionado);
    
            JOptionPane.showMessageDialog(null, "Categoría modificada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    
            // Volver al menú principal
            menuAVolver.actionPerformed(null);
        });
    
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> menuAVolver.actionPerformed(null));
    
        JPanel pnlBotones = new JPanel(new GridLayout(1, 2));
        pnlBotones.add(btnModificar);
        pnlBotones.add(btnCancelar);
    
        panel.add(pnlCategoria, BorderLayout.CENTER);
        panel.add(pnlBotones, BorderLayout.SOUTH);
        panel.revalidate();
        panel.repaint();
    }    

    public void elegirCategoria(ActionListener accion) {
        if (existenCategorias()) {
            panel.removeAll();
            panel.setLayout(new BorderLayout());
            TopBar.crearTopBar("Categorías", menuAVolver, panel);
    
            JPanel pnlCategorias = new JPanel(new GridLayout(0, 1));
    
            for (Categoria categoria : categorias) {
                JButton button = new JButton(categoria.toString());
                button.putClientProperty("categoria", categoria); // Asociar la categoría con el botón
                button.addActionListener(accion); // Asociar el listener al botón
                pnlCategorias.add(button);
            }
            panel.add(new JScrollPane(pnlCategorias), BorderLayout.CENTER);
            panel.revalidate();
            panel.repaint();
        }
    }

    public void eliminarCategoria() {
        if (existenCategorias()) {
            elegirCategoria(e -> {
                JButton button = (JButton) e.getSource();
                Categoria categoriaSeleccionada = (Categoria) button.getClientProperty("categoria");
    
                int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar la categoría?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
    
                if (opcion == JOptionPane.YES_OPTION) {
                    categorias.remove(categoriaSeleccionada);
                    JOptionPane.showMessageDialog(null, "Categoría eliminada con éxito.", "Categoría eliminada", JOptionPane.INFORMATION_MESSAGE);
                    menuAVolver.actionPerformed(null); // Regresar al menú principal
                }
            });
        }
    }    

    private JPanel generarPanelCategoria(Categoria categoria) {
        JPanel panelCategoria = new JPanel(new BorderLayout());
        JLabel nombreCategoria = new JLabel(categoria.getNombre());
    
        // Crear el ícono y el círculo del color correspondiente
        ImageIcon icono = categoria.getIcono();
        JPanel panelIconoColor = new JPanel(new BorderLayout());
        panelIconoColor.setBackground(categoria.getColor());
    
        JLabel labelIcono = new JLabel(icono);
        labelIcono.setHorizontalAlignment(SwingConstants.CENTER);
        panelIconoColor.add(labelIcono, BorderLayout.CENTER);
    
        // Agregar el ícono con el color al panel de la categoría
        panelCategoria.add(panelIconoColor, BorderLayout.WEST);
        panelCategoria.add(nombreCategoria, BorderLayout.CENTER);
    
        return panelCategoria;
    }
    
    public void mostrarCategorias() {
        if (existenCategorias()) {
            panel.removeAll();
            panel.setLayout(new BorderLayout());
            TopBar.crearTopBar("Categorías", menuAVolver, panel);
    
            JPanel pnlCategorias = new JPanel(new GridLayout(0, 1));
    
            for (Categoria categoria : categorias) {
                JPanel panelCategoria = generarPanelCategoria(categoria);
                pnlCategorias.add(panelCategoria);
            }
    
            JScrollPane scrollPane = new JScrollPane(pnlCategorias);
            panel.add(scrollPane, BorderLayout.CENTER);
    
            panel.revalidate();
            panel.repaint();
        }
    }
    
}
