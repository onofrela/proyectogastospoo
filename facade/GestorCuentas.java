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
}
