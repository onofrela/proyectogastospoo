package builder;

import categoria.Categoria;
import cuentas.Cuenta;

public interface BuilderEgreso extends BuilderRegistro {
    BuilderEgreso withCategoria(Categoria categoria); // Establece la categoría
    BuilderEgreso withCuentaAsociada(Cuenta cuenta); // Establece la cuenta asociada
}
