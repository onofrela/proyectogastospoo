package builder;

import java.time.LocalDateTime;

import registros.Registro;

public interface BuilderRegistro {
    BuilderRegistro withFecha(LocalDateTime fecha); // Asegura que haya una fecha
    BuilderRegistro withMonto(double monto); // Establece el monto
    Registro build(); // Construye el registro
}
