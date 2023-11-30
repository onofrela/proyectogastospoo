package test;

import java.util.List;
import cuentas.Cuenta;

public class Balance {
    
    public static void mostrarBalances(List<Cuenta> cuentas) {
        if(!cuentas.isEmpty()){
            System.out.println("----- Balances de Cuentas -----");
        
            for (Cuenta cuenta : cuentas) {
                double saldoCuenta = cuenta.getSaldo();
                System.out.println(cuenta.getNombre() + ": $" + saldoCuenta);
            }
        
            double balanceTotal = cuentas.stream().mapToDouble(Cuenta::getSaldo).sum();
        
            System.out.println("----- Balance Total -----");
            System.out.println("Balance Total: $" + balanceTotal);
        }else {
            System.out.println("Sin cuentas");
        }
    }
}
