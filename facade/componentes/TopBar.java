package facade.componentes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TopBar {
    private static JPanel icono;
    private static JPanel panelIcono = new JPanel(new GridBagLayout());

    public static void crearTopBar(String nombreMenu, ActionListener menuAVolver, JPanel panel) {
        JPanel topBar = new JPanel(new GridLayout(0, 1));
        JPanel contenido = new JPanel(new BorderLayout());
        topBar.setSize(50, 1200);
        JLabel titulo = new JLabel(nombreMenu);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setVerticalAlignment(SwingConstants.CENTER);
        titulo.setPreferredSize(new Dimension(200, 30));
        contenido.add(BotonRegreso.crearBotonRegreso(menuAVolver), BorderLayout.LINE_START);
        contenido.add(titulo, BorderLayout.CENTER);
        JPanel gap = new JPanel();
        gap.setSize(20, 20);
        gap.setBackground(topBar.getBackground());

        topBar.add(contenido);
        topBar.add(gap);
        panel.add(topBar, BorderLayout.PAGE_START);
    }

    public static void crearTopBarCategoria(String nombreMenu, ActionListener menuAVolver, JPanel panel, JPanel iconoNuevo) {
        if(icono == null)
            icono = iconoNuevo;
        else
            actualizarIcono(iconoNuevo);
        JPanel topBar = new JPanel(new GridLayout(0, 1));
        JPanel contenido = new JPanel(new BorderLayout());
        JPanel categoria = new JPanel(new BorderLayout());
        topBar.setSize(50, 1200);

        JLabel titulo = new JLabel(nombreMenu);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setVerticalAlignment(SwingConstants.CENTER);
        titulo.setPreferredSize(new Dimension(200, 30));

        panelIcono.setPreferredSize(new Dimension(50, 50));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panelIcono.add(icono, gbc);

        categoria.add(panelIcono, BorderLayout.LINE_START);
        categoria.add(titulo, BorderLayout.CENTER);

        contenido.add(BotonRegreso.crearBotonRegreso(menuAVolver), BorderLayout.LINE_START);
        contenido.add(categoria, BorderLayout.CENTER);

        JPanel gap = new JPanel();
        gap.setSize(20, 20);
        gap.setBackground(topBar.getBackground());

        topBar.add(contenido);
        topBar.add(gap);
        panel.add(topBar, BorderLayout.PAGE_START);
    }

    public static void actualizarIcono(JPanel iconoNuevo){
        if (iconoNuevo != null) {
            panelIcono.removeAll(); // Elimina cualquier componente en el panel de ícono
            panelIcono.add(iconoNuevo); // Agrega el nuevo ícono al panel
            panelIcono.revalidate(); // Revalida el panel para reflejar los cambios
            panelIcono.repaint(); // Repinta el panel para mostrar el nuevo ícono
        }
    }

    public static void crearTopBarMenu(String nombreMenu, ActionListener menuAVolver, JPanel panel) {
            JPanel topBar = new JPanel(new GridLayout(0, 1));
            JPanel contenido = new JPanel(new BorderLayout());
            topBar.setSize(50, 1200);
            JLabel titulo = new JLabel(nombreMenu);
            titulo.setHorizontalAlignment(SwingConstants.CENTER);
            titulo.setVerticalAlignment(SwingConstants.CENTER);
            titulo.setPreferredSize(new Dimension(200, 30));
            contenido.add(BotonMenu.crearBotonMenu(menuAVolver), BorderLayout.LINE_START);
            contenido.add(titulo, BorderLayout.CENTER);
            JPanel gap = new JPanel();
            gap.setSize(20, 20);
            gap.setBackground(topBar.getBackground());
            
            topBar.add(contenido);
            topBar.add(gap);
            panel.add(topBar, BorderLayout.PAGE_START);
        }
}
