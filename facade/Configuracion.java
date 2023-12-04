package facade;

import java.io.Serializable;

import monto.FormatoMonto;

public class Configuracion implements Serializable{
    private FormatoMonto formatoMonto;
    private String patron;
    private String idioma;
    private String pais;

    public Configuracion(FormatoMonto formatoMonto, String patron, String idioma, String pais) {
        this.formatoMonto = formatoMonto;
        this.patron = patron;
        this.idioma = "es";
        this.pais = "ES";
    }

    public FormatoMonto getFormatoMonto() {
        return this.formatoMonto;
    }

    public void setFormatoMonto(FormatoMonto formatoMonto) {
        this.formatoMonto = formatoMonto;
    }

    public String getPatron() {
        return this.patron;
    }

    public void setPatron(String patron) {
        this.patron = patron;
    }

    public String getIdioma() {
        return this.idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getPais() {
        return this.pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
