package builder;

import cuentas.Cuenta;

public interface BuilderTransaccion extends BuilderRegistro {
    BuilderTransaccion withCuentaOrigen(Cuenta cuentaOrigen); // Establece la cuenta de origen
    BuilderTransaccion withCuentaDestino(Cuenta cuentaDestino); // Establece la cuenta de destino
}
