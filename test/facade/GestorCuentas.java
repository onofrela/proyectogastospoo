package test.facade;

import java.util.List;
import java.util.Scanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import cuentas.Cuenta;
import test.facade.componentes.TopBar;;

public class GestorCuentas {
    private List<Cuenta> cuentas;
    private JPanel panel;
    private Scanner entrada;
    private ActionListener menuAVolver;
    private Cuenta cuentaSeleccionada;

    public GestorCuentas(List<Cuenta> cuentas, ActionListener menuAVolver, JPanel panel){
        this.panel = panel;
        this.cuentas = cuentas;
        this.entrada = new Scanner(System.in);
        this.menuAVolver = menuAVolver;
    }
    
    private boolean existenCuentas(){
        if(cuentas.isEmpty()){
            JOptionPane.showMessageDialog(null, "No existen cuentas. Por favor, primero agrega una cuenta.", "Cuentas no existentes", JOptionPane.WARNING_MESSAGE);
            return false;
        }else{
            return true;
        }
    }

    public void mostrarCuentas() {
        if (existenCuentas()) {
            panel.removeAll();
            panel.setLayout(new BorderLayout());
            TopBar.crearTopBar("Cuentas", menuAVolver, panel);
            JPanel pnlCuentas = new JPanel(new GridLayout(0, 1));
            JLabel textoCuenta;
            for (Cuenta cuenta : cuentas) {
                textoCuenta = new JLabel(cuenta.toString());
                textoCuenta.setHorizontalAlignment(SwingConstants.CENTER);
                pnlCuentas.add(textoCuenta);
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
                button.putClientProperty("cuenta", cuenta); // Asociar la cuenta con el botón
                button.addActionListener(accion); // Asociar el listener al botón
                pnlCuentas.add(button);
            }
            panel.add(new JScrollPane(pnlCuentas), BorderLayout.CENTER);
            panel.revalidate();
            panel.repaint();
        }
    }

    public void agregarCuenta() {
        System.out.println("----- Agregar Cuenta -----");
        System.out.print("Nombre de la cuenta: ");
        String nombreCuenta = entrada.nextLine();
        System.out.print("Saldo inicial de la cuenta: ");
        double saldoInicial = entrada.nextDouble();

        Cuenta nuevaCuenta = new Cuenta(nombreCuenta, saldoInicial);
        cuentas.add(nuevaCuenta);

        System.out.println("Cuenta agregada con éxito.");
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
        btnModificar.addActionListener(e -> {
            // Obtener los nuevos valores y actualizar la cuenta
            String nuevoNombre = txtNombreCuenta.getText();
            double nuevoSaldo = Double.parseDouble(txtSaldo.getText());
            
            // Actualizar los datos de la cuenta seleccionada
            cuentaSeleccionada.setNombre(nuevoNombre);
            cuentaSeleccionada.setSaldo(nuevoSaldo);

            // Volver al menú principal
            menuAVolver.actionPerformed(null);
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> menuAVolver.actionPerformed(null));

        JPanel pnlBotones = new JPanel(new GridLayout(1, 2));
        pnlBotones.add(btnModificar);
        pnlBotones.add(btnCancelar);

        panel.add(pnlCuenta, BorderLayout.CENTER);
        panel.add(pnlBotones, BorderLayout.SOUTH);
        panel.revalidate();
        panel.repaint();
    }

    public void editarCuenta() {
        if (existenCuentas()) 
            elegirCuenta(e -> {
                JButton button = (JButton) e.getSource();
                cuentaSeleccionada = (Cuenta) button.getClientProperty("cuenta");
                editarDatos();
            });
    }

    private ActionListener borrarCuenta = e -> {
        JButton button = (JButton) e.getSource();
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
