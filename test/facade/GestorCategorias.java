package test.facade;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import java.awt.*;

import categoria.Categoria;
import test.facade.componentes.TopBar;

public class GestorCategorias {
    private List<Categoria> categorias;
    private ActionListener menuAVolver;
    private JPanel panel;
    private Categoria categoriaSeleccionada;
    public Categoria categoriaNula;

    public GestorCategorias(List<Categoria> categorias, ActionListener menuAVolver, JPanel panel) {
        this.categorias = categorias;
        this.menuAVolver = menuAVolver;
        this.panel = panel;
        this.categoriaNula = new Categoria("Sin Categoria", "?", "Blanco");
    }

    public Categoria sinCategoria() {
        return categoriaNula;
    }

    public List<Categoria> getCategorias(){
        return this.categorias;
    }
    
    public void crearCategoria() {
        panel.removeAll();
        panel.setLayout(new BorderLayout());
        TopBar.crearTopBar("Crear nueva categoría", e -> menuAVolver.actionPerformed(null), panel);

        JPanel pnlCategoria = new JPanel(new GridLayout(3, 2));

        JLabel lblNombreCategoria = new JLabel("Nombre de la categoría:");
        JTextField txtNombreCategoria = new JTextField();

        JLabel lblIcono = new JLabel("Icono:");
        JTextField txtIcono = new JTextField();

        JLabel lblColor = new JLabel("Color:");
        JTextField txtColor = new JTextField();

        pnlCategoria.add(lblNombreCategoria);
        pnlCategoria.add(txtNombreCategoria);
        pnlCategoria.add(lblIcono);
        pnlCategoria.add(txtIcono);
        pnlCategoria.add(lblColor);
        pnlCategoria.add(txtColor);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(e -> {
            // Obtener los valores ingresados por el usuario
            String nuevoNombre = txtNombreCategoria.getText();
            String nuevoIcono = txtIcono.getText();
            String nuevoColor = txtColor.getText();

            // Verificar si ya existe una categoría con ese nombre
            if (existeCategoria(nuevoNombre)) {
                JOptionPane.showMessageDialog(null, "¡La categoría ya existe!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear una nueva categoría y agregarla a la lista
            Categoria nuevaCategoria = new Categoria(nuevoNombre, nuevoIcono, nuevoColor);
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
        JTextField txtIcono = new JTextField(categoriaSeleccionada.getIcono());
        JTextField txtColor = new JTextField(categoriaSeleccionada.getColor());
    
        pnlCategoria.add(lblNombreCategoria);
        pnlCategoria.add(txtNombreCategoria);
        pnlCategoria.add(lblIcono);
        pnlCategoria.add(txtIcono);
        pnlCategoria.add(lblColor);
        pnlCategoria.add(txtColor);
    
        JButton btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(e -> {
            // Obtener los nuevos valores y actualizar la categoría
            String nuevoNombre = txtNombreCategoria.getText();
            String nuevoIcono = txtIcono.getText();
            String nuevoColor = txtColor.getText();
    
            // Verificar si ya existe una categoría con ese nombre
            if (!nuevoNombre.equals(categoriaSeleccionada.getNombre()) && existeCategoria(nuevoNombre)) {
                JOptionPane.showMessageDialog(null, "¡Ya existe una categoría con ese nombre!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            // Actualizar los datos de la categoría seleccionada
            categoriaSeleccionada.setNombre(nuevoNombre);
            categoriaSeleccionada.setIcono(nuevoIcono);
            categoriaSeleccionada.setColor(nuevoColor);
    
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

    public void mostrarCategorias() {
        if (existenCategorias()) {
            panel.removeAll();
            panel.setLayout(new BorderLayout());
            TopBar.crearTopBar("Categorías", menuAVolver, panel);
            
            JPanel pnlCategorias = new JPanel(new GridLayout(0, 1));
            JLabel textoCategoria;
            
            for (Categoria categoria : categorias) {
                textoCategoria = new JLabel(categoria.toString());
                textoCategoria.setHorizontalAlignment(SwingConstants.CENTER);
                pnlCategorias.add(textoCategoria);
            }
            
            panel.add(pnlCategorias);
            panel.revalidate();
            panel.repaint();
        }
    }    
}
