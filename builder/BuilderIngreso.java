package builder;

import categoria.Categoria;
import cuentas.Cuenta;

public interface BuilderIngreso extends BuilderRegistro {
    BuilderIngreso withCategoria(Categoria categoria); // Establece la categoría
    BuilderIngreso withCuentaAsociada(Cuenta cuenta); // Establece la cuenta asociada
}
