package test.facade;

import java.util.List;
import cuentas.Cuenta;

public class Balance {
    
    public static String obtenerBalances(List<Cuenta> cuentas) {
        StringBuilder balances = new StringBuilder();
        
        if (!cuentas.isEmpty()) {
            balances.append("----- Balances de Cuentas -----\n");
        
            for (Cuenta cuenta : cuentas) {
                double saldoCuenta = cuenta.getSaldo();
                balances.append(cuenta.getNombre()).append(": $").append(saldoCuenta).append("\n");
            }
        
            double balanceTotal = cuentas.stream().mapToDouble(Cuenta::getSaldo).sum();
        
            balances.append("----- Balance Total -----\n");
            balances.append("Balance Total: $").append(balanceTotal).append("\n");
        } else {
            balances.append("Sin cuentas\n");
        }
        
        return balances.toString();
    }
}
