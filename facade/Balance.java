package facade;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import cuentas.Cuenta;

public class Balance {
    public JLabel textoCuentas;
    public JLabel textoTotal;
    public JLabel textoSinCuentas;
    public List<Cuenta> cuentas;

    public Balance(List<Cuenta> cuentas){
        this.cuentas = cuentas;

        this.textoCuentas = new JLabel("Balance de Cuentas");
        this.textoCuentas.setHorizontalAlignment(SwingConstants.CENTER);
        this.textoCuentas.setFont(new Font("Arial", Font.BOLD, 18));

        this.textoTotal = new JLabel("Balance Total");
        this.textoTotal.setHorizontalAlignment(SwingConstants.CENTER);
        this.textoTotal.setFont(new Font("Arial", Font.BOLD, 18));

        this.textoSinCuentas = new JLabel("Sin cuentas");
        this.textoSinCuentas.setHorizontalAlignment(SwingConstants.CENTER);
        this.textoSinCuentas.setFont(new Font("Arial", Font.BOLD, 18));
    }

    public JPanel generarBalance() {
        double saldoCuenta;
        String saldoFormateado, balanceCuenta;
        JLabel texto;
        JPanel pnlBalance = new JPanel(new GridLayout(0, 1));

        if (!cuentas.isEmpty()) {
            pnlBalance.add(this.textoCuentas);

            for (Cuenta cuenta : this.cuentas) {
                saldoCuenta = cuenta.getSaldo();
                saldoFormateado = String.format("%.2f", saldoCuenta);
                balanceCuenta = cuenta.getNombre() + ": $" + saldoFormateado;
                texto = new JLabel(balanceCuenta); 
                texto.setHorizontalAlignment(SwingConstants.CENTER);
                pnlBalance.add(texto);
            }

            double balanceTotal = cuentas.stream().mapToDouble(Cuenta::getSaldo).sum();
            String balanceTotalFormateado = String.format("%.2f", balanceTotal);
            pnlBalance.add(this.textoTotal);
            texto = new JLabel("$" + balanceTotalFormateado);
            texto.setHorizontalAlignment(SwingConstants.CENTER);
            pnlBalance.add(texto);

        } else {
            pnlBalance.add(this.textoSinCuentas);
        }
        JPanel gap = new JPanel();
        gap.setSize(20, 20);
        gap.setBackground(pnlBalance.getBackground());
        pnlBalance.add(gap);
        return pnlBalance;
    }
}
