import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.io.*;


public class Menu {
    private static Scanner entrada = new Scanner(System.in);
    private static List<Registro> registros = new ArrayList<>();
    private static List<Cuenta> cuentas = new ArrayList<>();
    private static final String ARCHIVO_CUENTAS = "cuentas.obj";
    private static final String ARCHIVO_TRANSACCIONES = "transacciones.obj";

    public static void guardarDatos() {
        try (ObjectOutputStream cuentasOut = new ObjectOutputStream(new FileOutputStream(ARCHIVO_CUENTAS));
             ObjectOutputStream transaccionesOut = new ObjectOutputStream(new FileOutputStream(ARCHIVO_TRANSACCIONES))) {

            cuentasOut.writeObject(cuentas);
            transaccionesOut.writeObject(registros);

        } catch (IOException e) {
            System.out.println("Error al guardar datos: " + e.getMessage());
        }
    }

    public static void cargarDatos() {
        try (ObjectInputStream cuentasIn = new ObjectInputStream(new FileInputStream(ARCHIVO_CUENTAS));
             ObjectInputStream transaccionesIn = new ObjectInputStream(new FileInputStream(ARCHIVO_TRANSACCIONES))) {
    
            cuentas = (List<Cuenta>) cuentasIn.readObject();
            registros = (List<Registro>) transaccionesIn.readObject();
    
           // Antes de actualizar saldos, restablecer los saldos a su estado inicial
            for (Cuenta cuenta : cuentas) {
                cuenta.setSaldo(0);
            }

            // Actualizar saldos de cuentas
            for (Registro registro : registros) {
                if (registro instanceof Transaccion) {
                    Transaccion transferencia = (Transaccion) registro;
                    String cuentaOrigenNombre = transferencia.getCuentaOrigen().getNombre();
                    String cuentaDestinoNombre = transferencia.getCuentaDestino().getNombre();

                    for (Cuenta cuenta : cuentas) {
                        if (cuenta.getNombre().equals(cuentaOrigenNombre)) {
                            cuenta.actualizarBalance(-transferencia.getMonto());
                        }

                        if (cuenta.getNombre().equals(cuentaDestinoNombre)) {
                            cuenta.actualizarBalance(transferencia.getMonto());
                        }
                    }
                }
            }
    
        } catch (FileNotFoundException e) {
            System.out.println("No se encontraron archivos de datos existentes.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar datos: " + e.getMessage());
        }
    }    

    public static void Principal() {
        int opcion;

        do {
            mostrarBalances();
            System.out.println("----- Menú Principal -----");
            System.out.println("1. Ver Transacciones");
            System.out.println("2. Gestionar Cuentas");
            System.out.println("3. Agregar Transacción");
            System.out.println("4. Generar Reportes");
            System.out.println("5. Configuración");
            System.out.println("6. Salir");
            System.out.print("Selecciona una opción (1-6): ");

            opcion = entrada.nextInt();
            entrada.nextLine();  // Limpiar el buffer de entrada

            switch (opcion) {
                case 1:
                    mostrarMenuTransacciones();
                    break;
                case 2:
                    mostrarMenuCuentas();
                    break;
                case 3:
                    agregarTransaccion();
                    break;
                case 4:
                    generarReportes();
                    break;
                case 5:
                    mostrarMenuConfiguracion();
                    break;
                case 6:
                    System.out.println("Saliendo de la aplicación. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
            }

        } while (opcion != 6);

        entrada.close();
    }

    private static void mostrarMenuTransacciones() {
        int opcion;

        do {
            System.out.println("----- Menú de Transacciones -----");
            System.out.println("1. Ver Todas las Transacciones");
            System.out.println("2. Volver al Menú Principal");
            System.out.print("Selecciona una opción (1-2): ");

            opcion = entrada.nextInt();
            entrada.nextLine();  // Limpiar el buffer de entrada

            switch (opcion) {
                case 1:
                    mostrarTodasLasTransacciones();
                    break;
                case 2:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
            }

        } while (opcion != 2);
    }

    private static void mostrarTodasLasTransacciones() {
        if (cuentas.isEmpty()) {
            System.out.println("Error: No existen cuentas. Por favor, primero agrega una cuenta.");
        } else {
            System.out.println("----- Todas las Transacciones -----");
            for (Registro registro : registros) {
                System.out.println(registro);
            }
        }
    }

    private static void mostrarMenuCuentas() {
        int opcion;

        do {
            System.out.println("----- Menú de Gestionar Cuentas -----");
            System.out.println("1. Ver Cuentas");
            System.out.println("2. Agregar Cuenta");
            System.out.println("3. Editar Cuenta");
            System.out.println("4. Eliminar Cuenta");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Selecciona una opción (1-5): ");

            opcion = entrada.nextInt();
            entrada.nextLine();  // Limpiar el buffer de entrada

            switch (opcion) {
                case 1:
                    mostrarCuentas();
                    break;
                case 2:
                    agregarCuenta();
                    break;
                case 3:
                    // Lógica para editar cuenta
                    break;
                case 4:
                    // Lógica para eliminar cuenta
                    break;
                case 5:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
            }

        } while (opcion != 5);
    }

    private static void mostrarCuentas() {
        if (cuentas.isEmpty()) {
            System.out.println("Error: No existen cuentas. Por favor, primero agrega una cuenta.");
        } else {
            System.out.println("----- Todas las Cuentas -----");
            for (Cuenta cuenta : cuentas) {
                System.out.println(cuenta);
            }
        }
    }

    private static void agregarCuenta() {
        System.out.println("----- Agregar Cuenta -----");
        System.out.print("Nombre de la cuenta: ");
        String nombreCuenta = entrada.nextLine();
        System.out.print("Saldo inicial de la cuenta: ");
        double saldoInicial = entrada.nextDouble();

        Cuenta nuevaCuenta = new Cuenta(nombreCuenta, saldoInicial);
        cuentas.add(nuevaCuenta);

        System.out.println("Cuenta agregada con éxito.");
    }

    private static void agregarTransaccion() {
        System.out.println("----- Agregar Transacción -----");
        if (cuentas.isEmpty()) {
            System.out.println("Error: No existen cuentas. Por favor, primero agrega una cuenta.");
        } else {
            System.out.print("Seleccione el tipo de transacción (1. Ingreso / 2. Egreso / 3. Transferencia): ");
            int tipoTransaccion = entrada.nextInt();
            entrada.nextLine();  // Limpiar el buffer de entrada

            System.out.print("Descripción: ");
            String descripcion = entrada.nextLine();

            System.out.print("Monto: ");
            double monto = entrada.nextDouble();
            entrada.nextLine();  // Limpiar el buffer de entrada

            System.out.print("Categoría (Solo para Ingresos y Egresos): ");
            String categoria = entrada.nextLine();

            Date fecha = new Date();  // Fecha actual por defecto

            switch (tipoTransaccion) {
                case 1:
                    mostrarCuentas();
                    System.out.print("Seleccione la cuenta a asociar: ");
                    int indexCuentaIngreso = entrada.nextInt();
                    entrada.nextLine();  // Limpiar el buffer de entrada
                    if (indexCuentaIngreso >= 0 && indexCuentaIngreso < cuentas.size()) {
                        Cuenta cuentaIngreso = cuentas.get(indexCuentaIngreso);
                        Ingreso ingreso = new Ingreso(fecha, descripcion, monto, categoria, cuentaIngreso);
                        registros.add(ingreso);
                        cuentaIngreso.actualizarBalance(monto);
                    } else {
                        System.out.println("Error: Índice de cuenta no válido.");
                    }
                    break;
                case 2:
                    mostrarCuentas();
                    System.out.print("Seleccione la cuenta a asociar: ");
                    int indexCuentaEgreso = entrada.nextInt();
                    entrada.nextLine();  // Limpiar el buffer de entrada
                    if (indexCuentaEgreso >= 0 && indexCuentaEgreso < cuentas.size()) {
                        Cuenta cuentaEgreso = cuentas.get(indexCuentaEgreso);
                        Egreso egreso = new Egreso(fecha, descripcion, monto, categoria, cuentaEgreso);
                        registros.add(egreso);
                        cuentaEgreso.actualizarBalance(-monto);
                    } else {
                        System.out.println("Error: Índice de cuenta no válido.");
                    }
                    break;
                    case 3:
                    mostrarCuentas();
                    System.out.print("Cuenta de origen (índice): ");
                    int indexCuentaOrigen = entrada.nextInt();
                    entrada.nextLine();  // Limpiar el buffer de entrada
                    if (indexCuentaOrigen >= 0 && indexCuentaOrigen < cuentas.size()) {
                        Cuenta cuentaOrigen = cuentas.get(indexCuentaOrigen);
                
                        mostrarCuentas();
                        System.out.print("Cuenta de destino (índice): ");
                        int indexCuentaDestino = entrada.nextInt();
                        entrada.nextLine();  // Limpiar el buffer de entrada
                        if (indexCuentaDestino >= 0 && indexCuentaDestino < cuentas.size()) {
                            Cuenta cuentaDestino = cuentas.get(indexCuentaDestino);
                
                            Transaccion transferencia = new Transaccion(fecha, descripcion, monto, cuentaOrigen, cuentaDestino);
                            registros.add(transferencia);
                            cuentas.add(cuentaOrigen);
                            cuentas.add(cuentaDestino);
                
                            cuentaOrigen.actualizarBalance(-monto);
                            cuentaDestino.actualizarBalance(monto);
                        } else {
                            System.out.println("Error: Índice de cuenta de destino no válido.");
                        }
                    } else {
                        System.out.println("Error: Índice de cuenta de origen no válido.");
                    }
                    break;                
                default:
                    System.out.println("Tipo de transacción no válido.");
                    break;
            }

            System.out.println("Transacción agregada con éxito.");
        }
    }

    private static void generarReportes() {
        // Lógica para generar reportes
    }

    private static void mostrarMenuConfiguracion() {
        int opcion;

        do {
            System.out.println("----- Menú de Configuración -----");
            System.out.println("1. Configurar Moneda Predeterminada");
            System.out.println("2. Configurar Formato de Fecha");
            System.out.println("3. Volver al Menú Principal");
            System.out.print("Selecciona una opción (1-3): ");

            opcion = entrada.nextInt();
            entrada.nextLine();  // Limpiar el buffer de entrada

            switch (opcion) {
                case 1:
                    // Lógica para configurar moneda
                    break;
                case 2:
                    // Lógica para configurar formato de fecha
                    break;
                case 3:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
            }

        } while (opcion != 3);
    }

    private static void mostrarBalances() {
        System.out.println("----- Balances de Cuentas -----");
    
        for (Cuenta cuenta : cuentas) {
            double saldoCuenta = cuenta.getSaldo();
            System.out.println(cuenta.getNombre() + ": " + saldoCuenta);
        }
    
        double balanceTotal = cuentas.stream().mapToDouble(Cuenta::getSaldo).sum();
    
        System.out.println("----- Balance Total -----");
        System.out.println("Balance Total: " + balanceTotal);
    }
    
}
