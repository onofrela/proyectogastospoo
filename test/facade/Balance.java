package test.facade;

import java.util.List;

import javax.swing.JTextArea;

import cuentas.Cuenta;

public class Balance {
    
    public static void mostrarBalances(List<Cuenta> cuentas, JTextArea areaBalance) {
    if (!cuentas.isEmpty()) {
        StringBuilder balances = new StringBuilder("----- Balances de Cuentas -----\n");

        for (Cuenta cuenta : cuentas) {
            double saldoCuenta = cuenta.getSaldo();
            balances.append(cuenta.getNombre()).append(": $").append(saldoCuenta).append("\n");
        }

        double balanceTotal = cuentas.stream().mapToDouble(Cuenta::getSaldo).sum();

        balances.append("----- Balance Total -----\n");
        balances.append("Balance Total: $").append(balanceTotal);

        areaBalance.setText(balances.toString()); // Actualiza el área de texto con el balance
    } else {
        areaBalance.setText("Sin cuentas");
    }
}

}
