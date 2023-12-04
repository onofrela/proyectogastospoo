package facade;

import javax.swing.*;

import categoria.Categoria;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cuentas.Cuenta;
import monto.Monto;
import registros.Egreso;
import registros.Ingreso;
import registros.Registro;

public class Balance {
    public JLabel textoCuentas;
    public JLabel textoTotal;
    public JLabel textoSinCuentas;
    public List<Registro> registros;
    public List<Cuenta> cuentas;
    public List<Categoria> categorias;
    private Configuracion configuracion;

    public Balance(List<Categoria> categorias, List<Registro> registros,List<Cuenta> cuentas, Configuracion configuracion){
        this.categorias = categorias;
        this.registros = registros;
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

    public double obtenerBalanceTotal(){
        return this.cuentas.stream().mapToDouble(Cuenta::getSaldo).sum();
    }

    public double calcularBalanceIngresosPorCategoria(Categoria categoria) {
        double balanceCategoria = 0.0;
        
        for (Registro registro : this.registros) {
            if (registro instanceof Ingreso) {
                Ingreso ingreso = (Ingreso) registro;
                if (ingreso.getCategoria().equals(categoria)) {
                    balanceCategoria += ingreso.getMonto();
                }
            }
        }
        
        return balanceCategoria;
    }

    public Map<Categoria, Double> darMapaBalanceIngresoPorCategoria() {
        Map<Categoria, Double> balanceCategorias = new HashMap<>();
        
        for (Categoria categoria : this.categorias) {
            double balanceCategoria = calcularBalanceIngresosPorCategoria(categoria);
            balanceCategorias.put(categoria, balanceCategoria);
        }
        
        return balanceCategorias;
    }

    public double calcularBalanceEgresosPorCategoria(Categoria categoria) {
        double balanceCategoria = 0.0;
        
        for (Registro registro : this.registros) {
            if (registro instanceof Egreso) {
                Egreso egreso = (Egreso) registro;
                if (egreso.getCategoria().equals(categoria)) {
                    balanceCategoria += egreso.getMonto();
                }
            }
        }
        return balanceCategoria;
    }

    public Map<Categoria, Double> darMapaBalanceEgresoPorCategoria() {
        Map<Categoria, Double> balanceCategorias = new HashMap<>();
        
        for (Categoria categoria : this.categorias) {
            double balanceCategoria = calcularBalanceEgresosPorCategoria(categoria);
            balanceCategorias.put(categoria, balanceCategoria);
        }
        return balanceCategorias;
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

            double balanceTotal = obtenerBalanceTotal();
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
