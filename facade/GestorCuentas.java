package facade;

import java.util.List;
import java.util.Scanner;

import cuentas.Cuenta;;

public class GestorCuentas {
    private List<Cuenta> cuentas;
    private Scanner entrada;

    public GestorCuentas(List<Cuenta> cuentas, Scanner entrada){
        this.entrada = entrada;
        this.cuentas = cuentas;
    }

    public GestorCuentas(List<Cuenta> cuentas){
        this.entrada = new Scanner(System.in);
        this.cuentas = cuentas;
    }
    
    public void mostrarCuentas() {
        if (cuentas.isEmpty()) {
            System.out.println("Error: No existen cuentas. Por favor, primero agrega una cuenta.");
        } else {
            System.out.println("----- Todas las Cuentas -----");
            for (Cuenta cuenta : cuentas) {
                System.out.println(cuenta);
            }
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
    
    public void editarCuenta() {
        if (cuentas.isEmpty()) {
            System.out.println("Error: No hay cuentas para editar.");
            return;
        }

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

    public void eliminarCuenta() {
        if (cuentas.isEmpty()) {
            System.out.println("Error: No hay cuentas para eliminar.");
            return;
        }

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
