package facade;

import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.List;

import javax.swing.*;
import java.awt.*;

import categoria.Categoria;
import facade.componentes.BotonCancelar;
import facade.componentes.TopBar;
import facade.componentes.colores.Colores;
import facade.estilos.Estilos;

public class GestorCategorias {
    private List<Categoria> categorias;
    public Categoria categoriaNula;

    private JPanel panel;
    private Color[] colores;
    private JTextField txtNombreCategoria;
    private ActionListener menuAVolver;

    private Categoria categoriaSeleccionada;
    private ImageIcon iconoSeleccionado;
    private Color colorSeleccionado;

    public GestorCategorias(List<Categoria> categorias, ActionListener menuAVolver, JPanel panel) {
        this.txtNombreCategoria = new JTextField();
        this.categorias = categorias;
        this.menuAVolver = menuAVolver;
        this.panel = panel;
        java.net.URL imgURL = getClass().getResource("/categoria/iconos/unknown.png");
        ImageIcon icono = new ImageIcon(imgURL);
        this.colores = Colores.obtenerColores();

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
        Color[] coloresPermitidos = colores; // Lista de colores permitidos
    
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
                    this.iconoSeleccionado = icono;
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
    
    public void panelDatosCategoria(String tituloTopBar, JButton accion) {
        panel.removeAll();
        panel.setLayout(new BorderLayout());
        TopBar.crearTopBarCategoria(tituloTopBar, e -> menuAVolver.actionPerformed(null), panel, generarIcono(iconoSeleccionado, colorSeleccionado));
    
        JPanel pnlCategoria = new JPanel(new GridLayout(3, 2));
    
        JLabel lblNombreCategoria = new JLabel("Nombre de la categoría:");
    
        JLabel lblIcono = new JLabel("Icono:");
        JButton btnSeleccionarIcono = new JButton("Seleccionar Icono");
        Estilos.estilizarBoton(btnSeleccionarIcono);
    
        JLabel lblColor = new JLabel("Color:");
        JButton btnSeleccionarColor = new JButton("Seleccionar Color");
        Estilos.estilizarBoton(btnSeleccionarColor);
    
        pnlCategoria.add(lblNombreCategoria);
        pnlCategoria.add(this.txtNombreCategoria);
        pnlCategoria.add(lblIcono);
        pnlCategoria.add(btnSeleccionarIcono);
        pnlCategoria.add(lblColor);
        pnlCategoria.add(btnSeleccionarColor);
    
        btnSeleccionarColor.addActionListener(e -> {
            elegirColor();
            TopBar.actualizarIcono(generarIcono(iconoSeleccionado, colorSeleccionado));
        });
    
        btnSeleccionarIcono.addActionListener(e -> {
            elegirIcono();
            TopBar.actualizarIcono(generarIcono(iconoSeleccionado, colorSeleccionado));
        });

        JPanel pnlBotones = new JPanel(new GridLayout(1, 2));
        pnlBotones.add(accion);
        pnlBotones.add(BotonCancelar.crearBoton(menuAVolver));
    
        panel.add(pnlCategoria, BorderLayout.CENTER);
        panel.add(pnlBotones, BorderLayout.SOUTH);
        panel.revalidate();
        panel.repaint();
    }    

    public void editarDatosCategoria() {
        JButton btnModificar = new JButton("Modificar");
        Estilos.estilizarBoton(btnModificar);
        btnModificar.addActionListener(e -> {
            String nuevoNombre = txtNombreCategoria.getText();
            if (!nuevoNombre.equals(categoriaSeleccionada.getNombre()) && existeCategoria(nuevoNombre)) {
                JOptionPane.showMessageDialog(null, "¡Ya existe una categoría con ese nombre!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            categoriaSeleccionada.setNombre(nuevoNombre);
            categoriaSeleccionada.setIcono(iconoSeleccionado);
            categoriaSeleccionada.setColor(colorSeleccionado);
    
            JOptionPane.showMessageDialog(null, "Categoría modificada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    
            menuAVolver.actionPerformed(null);
        });
        this.txtNombreCategoria.setText(categoriaSeleccionada.getNombre());
        panelDatosCategoria("Modificar categoría " + categoriaSeleccionada.getNombre(), btnModificar);
    }    

    public void crearCategoria() {
        this.colorSeleccionado = Color.RED;
        this.iconoSeleccionado = new ImageIcon(getClass().getResource("/categoria/iconos/box.png"));
        this.txtNombreCategoria.setText("");
        JButton btnAgregar = new JButton("Agregar");
        Estilos.estilizarBoton(btnAgregar);
        btnAgregar.addActionListener(e -> {
            String nuevoNombre = this.txtNombreCategoria.getText();

            if (existeCategoria(nuevoNombre)) {
                JOptionPane.showMessageDialog(null, "¡La categoría ya existe!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            Categoria nuevaCategoria = new Categoria(nuevoNombre, iconoSeleccionado, colorSeleccionado);
            categorias.add(nuevaCategoria);
    
            JOptionPane.showMessageDialog(null, "Categoría creada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    
            menuAVolver.actionPerformed(null);
        });
        panelDatosCategoria("Crear nueva categoría", btnAgregar);
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
                Estilos.estilizarBoton(button);
                categoriaSeleccionada = (Categoria) button.getClientProperty("categoria");
                iconoSeleccionado = categoriaSeleccionada.getIcono();
                colorSeleccionado = categoriaSeleccionada.getColor();
                editarDatosCategoria();
            });
        }
    }
    
    public void elegirCategoria(ActionListener accion) {
        if (existenCategorias()) {
            panel.removeAll();
            panel.setLayout(new BorderLayout());
            TopBar.crearTopBar("Categorías", menuAVolver, panel);
    
            JPanel pnlCategorias = new JPanel(new GridLayout(0, 1, 10, 10));
    
            for (Categoria categoria : categorias) {
                JButton button = new JButton(categoria.toString());
                Estilos.estilizarBoton(button);
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

    public JPanel generarIcono(Categoria categoria) {
        ImageIcon icono = categoria.getIcono();
        Color color = categoria.getColor();

        // Crear el panel que contendrá el ícono circular
        JPanel panelIconoColor = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int diameter = Math.min(getWidth(), getHeight());
                g.setColor(color);
                g.fillOval(0, 0, diameter, diameter);

                int iconSize = 25; // Tamaño del ícono dentro del círculo
                int x = (diameter - iconSize) / 2;
                int y = (diameter - iconSize) / 2;

                g.drawImage(icono.getImage(), x, y, iconSize, iconSize, null);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(50, 50);
            }
        };

        return panelIconoColor;
    }

    public JPanel generarIcono(ImageIcon icono, Color color) {
        // Crear el panel que contendrá el ícono circular
        JPanel panelIconoColor = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int diameter = Math.min(getWidth(), getHeight());
                g.setColor(color);
                g.fillOval(0, 0, diameter, diameter);

                int iconSize = 25; // Tamaño del ícono dentro del círculo
                int x = (diameter - iconSize) / 2;
                int y = (diameter - iconSize) / 2;

                g.drawImage(icono.getImage(), x, y, iconSize, iconSize, null);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(50, 50);
            }
        };

        return panelIconoColor;
    }

    private JPanel generarPanelCategoria(Categoria categoria) {
        // Crear el ícono
        JPanel panelCategoria = new JPanel();
        panelCategoria.setPreferredSize(new Dimension(240, 60));
        panelCategoria.setLayout(new BorderLayout());

        JPanel panelIcono = new JPanel(new GridBagLayout());
        panelIcono.setPreferredSize(new Dimension(60, 60));
        
        JPanel icono = generarIcono(categoria);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        
        panelIcono.add(icono, gbc);

        JLabel nombreCategoria = new JLabel(categoria.getNombre());
        nombreCategoria.setVerticalAlignment(SwingConstants.CENTER);
        nombreCategoria.setHorizontalAlignment(SwingConstants.LEFT);
        nombreCategoria.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0)); // Agrega un margen a la izquierda del texto

        panelCategoria.add(panelIcono, BorderLayout.WEST);
        panelCategoria.add(nombreCategoria, BorderLayout.CENTER);
        return panelCategoria;
    }
    
    public void mostrarCategorias() {
        if (existenCategorias()) {
            panel.removeAll();
            panel.setLayout(new BorderLayout());
            TopBar.crearTopBar("Categorías", menuAVolver, panel);
    
            JPanel pnlCategorias = new JPanel();
            pnlCategorias.setLayout(new BoxLayout(pnlCategorias, BoxLayout.Y_AXIS));
    
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
