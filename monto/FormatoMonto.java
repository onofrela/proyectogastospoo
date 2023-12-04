package monto;

import java.io.Serializable;

public class FormatoMonto  implements Serializable{
    private String simboloMoneda;
    private String moneda;

    public FormatoMonto(String simboloMoneda, String moneda) {
        this.simboloMoneda = simboloMoneda;
        this.moneda = moneda;
    }

    public String getSimboloMoneda() {
        return this.simboloMoneda;
    }

    public void setSimboloMoneda(String simboloMoneda) {
        this.simboloMoneda = simboloMoneda;
    }

    public String getMoneda() {
        return this.moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }
    
}
