package facade;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import cuentas.Cuenta;
import monto.Monto;

public class Balance {
    public JLabel textoCuentas;
    public JLabel textoTotal;
    public JLabel textoSinCuentas;
    public List<Cuenta> cuentas;
    private Configuracion configuracion;

    public Balance(List<Cuenta> cuentas, Configuracion configuracion){
        this.cuentas = cuentas;
        this.configuracion = configuracion;

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
        Monto saldoCuenta;
        String balanceCuenta;
        JLabel texto;
        JPanel pnlBalance = new JPanel(new GridLayout(0, 1));

        if (!cuentas.isEmpty()) {
            pnlBalance.add(this.textoCuentas);

            for (Cuenta cuenta : this.cuentas) {
                saldoCuenta = cuenta.getSaldoFormateado();
                balanceCuenta = cuenta.getNombre() + ": " + saldoCuenta;
                texto = new JLabel(balanceCuenta); 
                texto.setHorizontalAlignment(SwingConstants.CENTER);
                pnlBalance.add(texto);
            }

            double balanceTotal = cuentas.stream().mapToDouble(Cuenta::getSaldo).sum();
            pnlBalance.add(this.textoTotal);

            String textoTotal = this.configuracion.getFormatoMonto().getSimboloMoneda() + String.format("%.2f", balanceTotal) + " " + this.configuracion.getFormatoMonto().getMoneda();
            texto = new JLabel(textoTotal);
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
