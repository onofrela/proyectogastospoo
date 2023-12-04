package builder;

import cuentas.Cuenta.CuentaBuilder;

public interface BuilderCuenta {
    public abstract CuentaBuilder withNombre(String nombre);
}
