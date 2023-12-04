package facade.componentes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TopBar {
    public static void crearTopBar(String nombreMenu, ActionListener menuAVolver, JPanel panel) {
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setSize(50, 1200);
        JLabel titulo = new JLabel(nombreMenu);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setVerticalAlignment(SwingConstants.CENTER);
        titulo.setPreferredSize(new Dimension(200, 30));
        topBar.add(BotonRegreso.crearBotonRegreso(menuAVolver), BorderLayout.LINE_START);
        topBar.add(titulo, BorderLayout.CENTER);
        panel.add(topBar, BorderLayout.PAGE_START);
    }

public static void crearTopBarMenu(String nombreMenu, ActionListener menuAVolver, JPanel panel) {
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setSize(50, 1200);
        JLabel titulo = new JLabel(nombreMenu);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setVerticalAlignment(SwingConstants.CENTER);
        titulo.setPreferredSize(new Dimension(200, 30));
        topBar.add(BotonMenu.crearBotonMenu(menuAVolver), BorderLayout.LINE_START);
        topBar.add(titulo, BorderLayout.CENTER);
        panel.add(topBar, BorderLayout.PAGE_START);
    }
}
