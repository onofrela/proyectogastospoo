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
        panel.removeAll();
        panel.setLayout(new BorderLayout());
        TopBar.crearTopBar("Cuentas", menuAVolver, panel);
        JPanel pnlCuentas = new JPanel(new GridLayout(0, 1));
        JLabel textoCuenta;
        if (existenCuentas()) {
            JLabel titulo = new JLabel("Todas las Cuentas");
            titulo.setHorizontalAlignment(SwingConstants.CENTER);
            titulo.setFont(new Font("Arial", Font.BOLD, 18));
            for (Cuenta cuenta : cuentas) {
                textoCuenta = new JLabel(cuenta.toString());
                textoCuenta.setHorizontalAlignment(SwingConstants.CENTER);
                pnlCuentas.add(textoCuenta);
            }
        }
        panel.add(pnlCuentas);
        panel.revalidate();
        panel.repaint();
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

    public void editarCuenta() {
        if (existenCuentas()) {
            mostrarCuentas();
            System.out.print("Ingrese el nombre de la cuenta a editar: ");
            String nombreCuenta = entrada.nextLine();

            for (Cuenta cuenta : cuentas) {
                if (cuenta.getNombre().equals(nombreCuenta)) {
                    System.out.println("¿Qué desea editar?");
                    System.out.println("1. Nombre de la cuenta");
                    System.out.println("2. Saldo de la cuenta");
                    int opcion = entrada.nextInt();
                    entrada.nextLine(); // Limpiar el buffer

                    switch (opcion) {
                        case 1:
                            System.out.print("Ingrese el nuevo nombre: ");
                            String nuevoNombre = entrada.nextLine();
                            cuenta.setNombre(nuevoNombre);
                            System.out.println("Nombre de la cuenta actualizado con éxito.");
                            break;
                        case 2:
                            System.out.print("Ingrese el nuevo saldo: ");
                            double nuevoSaldo = entrada.nextDouble();
                            cuenta.setSaldo(nuevoSaldo);
                            System.out.println("Saldo de la cuenta actualizado con éxito.");
                            break;
                        default:
                            System.out.println("Opción no válida.");
                            break;
                    }
                    return;
                }
            }

            System.out.println("Cuenta no encontrada.");
        }
    }

    public void eliminarCuenta() {
        if (existenCuentas()) {
            mostrarCuentas();
            System.out.print("Ingrese el nombre de la cuenta a eliminar: ");
            String nombreCuenta = entrada.nextLine();
            for (Cuenta cuenta : cuentas) {
                if (cuenta.getNombre().equals(nombreCuenta)) {
                    cuentas.remove(cuenta);
                    System.out.println("Cuenta eliminada con éxito.");
                    return;
                }
            }
            System.out.println("Cuenta no encontrada.");
        }
    }
}
