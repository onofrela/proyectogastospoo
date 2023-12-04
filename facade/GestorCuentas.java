package facade;

import java.util.List;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

import cuentas.Cuenta;
import facade.componentes.BotonCancelar;
import facade.componentes.TopBar;
import facade.estilos.Estilos;
import monto.Monto;;

public class GestorCuentas {
    private List<Cuenta> cuentas;
    private Cuenta cuentaSeleccionada;
    private Configuracion configuracion;
    private JPanel panel;
    private ActionListener menuAVolver;

    public GestorCuentas(List<Cuenta> cuentas, ActionListener menuAVolver, JPanel panel, Configuracion configuracion){
        this.panel = panel;
        this.cuentas = cuentas;
        this.menuAVolver = menuAVolver;
        this.configuracion = configuracion;
    }
    
    public List<Cuenta> getCuentas(){
        return this.cuentas;
    }

    public boolean existenCuentas(){
        if(cuentas.isEmpty()){
            JOptionPane.showMessageDialog(null, "No existen cuentas. Por favor, primero agrega una cuenta.", "Cuentas no existentes", JOptionPane.WARNING_MESSAGE);
            return false;
        }else{
            return true;
        }
    }

    public JPanel generarCartaCuenta(Cuenta cuenta) {
        JPanel carta = new JPanel(new BorderLayout());
        JLabel textoCuenta = new JLabel();
        Monto saldoCuenta = cuenta.getSaldoFormateado();
        String balanceCuenta = cuenta.getNombre() + ": " + saldoCuenta + "\n";
        textoCuenta.setText(balanceCuenta);
        textoCuenta.setHorizontalAlignment(SwingConstants.CENTER);
        textoCuenta.setForeground(new Color (249, 248, 237));
        textoCuenta.setFont(new Font("Segoe UI", Font.BOLD, 17));
        carta.add(textoCuenta);
        carta.setBackground(new Color(8, 129, 163));
        return carta;
    }

    public void mostrarCuentas() {
        if (existenCuentas()) {
            panel.removeAll();
            panel.setLayout(new BorderLayout());
            TopBar.crearTopBar("Cuentas", menuAVolver, panel);
            
            JPanel pnlCuentas = new JPanel(new GridLayout(0, 1, 50, 50));
            for (Cuenta cuenta : cuentas) {
                pnlCuentas.add(generarCartaCuenta(cuenta));
            }
            
            panel.add(pnlCuentas);
            panel.revalidate();
            panel.repaint();
        }
    }

    public void elegirCuenta(ActionListener accion) {
        if (existenCuentas()) {
            panel.removeAll();
            panel.setLayout(new BorderLayout());
            TopBar.crearTopBar("Cuentas", menuAVolver, panel);

            JPanel pnlCuentas = new JPanel(new GridLayout(0, 1));

            for (Cuenta cuenta : cuentas) {

                JButton button = new JButton(cuenta.toString());
                Estilos.estilizarBoton(button);
                button.putClientProperty("cuenta", cuenta);
                button.addActionListener(accion);
                pnlCuentas.add(button);
            }
            panel.add(new JScrollPane(pnlCuentas), BorderLayout.CENTER);
            panel.revalidate();
            panel.repaint();
        }
    }

    public void agregarCuenta() {
        panel.removeAll();
        panel.setLayout(new BorderLayout());
        TopBar.crearTopBar("Agregar nueva cuenta", e -> menuAVolver.actionPerformed(null), panel);

        JPanel pnlCuenta = new JPanel(new GridLayout(2, 2));

        JLabel lblNombreCuenta = new JLabel("Nombre de la cuenta:");
        JLabel lblSaldo = new JLabel("Saldo inicial:");

        JTextField txtNombreCuenta = new JTextField();
        JTextField txtSaldo = new JTextField();

        pnlCuenta.add(lblNombreCuenta);
        pnlCuenta.add(txtNombreCuenta);
        pnlCuenta.add(lblSaldo);
        pnlCuenta.add(txtSaldo);

        JButton btnAgregar = new JButton("Agregar");
        Estilos.estilizarBoton(btnAgregar);
        btnAgregar.addActionListener(e -> {
            String nuevoNombre = txtNombreCuenta.getText();
            double nuevoSaldo = Double.parseDouble(txtSaldo.getText());

            if (existeCuenta(nuevoNombre)) {
                JOptionPane.showMessageDialog(null, "¡La cuenta ya existe!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Cuenta nuevaCuenta = new Cuenta(nuevoNombre, nuevoSaldo, this.configuracion);
            cuentas.add(nuevaCuenta);

            JOptionPane.showMessageDialog(null, "Cuenta agregada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            menuAVolver.actionPerformed(null);
        });

        JPanel pnlBotones = new JPanel(new GridLayout(1, 2));
        pnlBotones.add(btnAgregar);
        pnlBotones.add(BotonCancelar.crearBoton(menuAVolver));

        panel.add(pnlCuenta, BorderLayout.CENTER);
        panel.add(pnlBotones, BorderLayout.SOUTH);
        panel.revalidate();
        panel.repaint();
    }    

    private boolean existeCuenta(String nombre) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

    public void editarDatos() {
        panel.removeAll();
        panel.setLayout(new BorderLayout());
        TopBar.crearTopBar("Modificar cuenta " + cuentaSeleccionada.getNombre(), e -> menuAVolver.actionPerformed(null), panel);
    
        JPanel pnlCuenta = new JPanel(new GridLayout(2, 2));
    
        JLabel lblNombreCuenta = new JLabel("Nombre de la cuenta:");
        JLabel lblSaldo = new JLabel("Saldo:");
    
        JTextField txtNombreCuenta = new JTextField(cuentaSeleccionada.getNombre());
        JTextField txtSaldo = new JTextField(String.valueOf(cuentaSeleccionada.getSaldo()));
    
        pnlCuenta.add(lblNombreCuenta);
        pnlCuenta.add(txtNombreCuenta);
        pnlCuenta.add(lblSaldo);
        pnlCuenta.add(txtSaldo);
    
        JButton btnModificar = new JButton("Modificar");
        Estilos.estilizarBoton(btnModificar);
        btnModificar.addActionListener(e -> {
            String nuevoNombre = txtNombreCuenta.getText();
            double nuevoSaldo = Double.parseDouble(txtSaldo.getText());
            
            if (!nuevoNombre.equals(cuentaSeleccionada.getNombre()) && existeCuenta(nuevoNombre)) {
                JOptionPane.showMessageDialog(null, "¡Ya existe una cuenta con ese nombre!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            cuentaSeleccionada.setNombre(nuevoNombre);
            cuentaSeleccionada.setSaldo(nuevoSaldo);
    
            JOptionPane.showMessageDialog(null, "Cuenta modificada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            menuAVolver.actionPerformed(null);
        });
    
        JPanel pnlBotones = new JPanel(new GridLayout(1, 2));
        pnlBotones.add(btnModificar);
        pnlBotones.add(BotonCancelar.crearBoton(menuAVolver));
    
        panel.add(pnlCuenta, BorderLayout.CENTER);
        panel.add(pnlBotones, BorderLayout.SOUTH);
        panel.revalidate();
        panel.repaint();
    }
    
    public void editarCuenta() {
        if (existenCuentas()) 
            elegirCuenta(e -> {
                JButton button = (JButton) e.getSource();
                Estilos.estilizarBoton(button);
                cuentaSeleccionada = (Cuenta) button.getClientProperty("cuenta");
                editarDatos();
            });
    }    

    private ActionListener borrarCuenta = e -> {
        JButton button = (JButton) e.getSource();
        Estilos.estilizarBoton(button);
        cuentaSeleccionada = (Cuenta) button.getClientProperty("cuenta");
        
        int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar la cuenta?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        
        if (opcion == JOptionPane.YES_OPTION) {
            cuentas.remove(cuentaSeleccionada);
            JOptionPane.showMessageDialog(null, "Cuenta eliminada con éxito.", "Cuenta eliminada", JOptionPane.INFORMATION_MESSAGE);
            menuAVolver.actionPerformed(null); // Regresar al menú principal
        }
    };

    public void eliminarCuenta() {
        if (existenCuentas()) {
            elegirCuenta(borrarCuenta);
        }
    }
}
