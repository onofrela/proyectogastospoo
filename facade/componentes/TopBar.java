package facade.componentes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TopBar {
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
