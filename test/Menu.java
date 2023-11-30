package test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import categoria.Categoria;
import cuentas.Cuenta;
import registros.Egreso;
import registros.Ingreso;
import registros.Registro;
import registros.Transaccion;
import datos.ManejoArchivos;

public class Menu {
    private static Scanner entrada = new Scanner(System.in);
    private static List<Registro> registros = new ArrayList<>();
    private static List<Cuenta> cuentas = new ArrayList<>();
    private static List<Categoria> categorias = new ArrayList<>();
    public static void Principal() {
        int opcion;
        ManejoArchivos.cargarDatos(registros, cuentas, categorias);
        do {
            Balance.mostrarBalances(cuentas);
            System.out.println("----- Menú Principal -----");
            System.out.println("1. Ver Transacciones");
            System.out.println("2. Gestionar Cuentas");
            System.out.println("3. Gestionar Categorías");
            System.out.println("4. Agregar Transacción");
            System.out.println("5. Generar Reportes");
            System.out.println("6. Configuración");
            System.out.println("7. Salir");
            System.out.print("Selecciona una opción (1-7): ");

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
                    mostrarMenuCategorias();
                    break;
                case 4:
                    agregarTransaccion();
                    break;
                case 5:
                    generarReportes();
                    break;
                case 6:
                    mostrarMenuConfiguracion();
                    break;
                case 7:
                    System.out.println("Saliendo de la aplicación. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
            }

        } while (opcion != 7);

        ManejoArchivos.guardarDatos(registros, cuentas, categorias);
        entrada.close();
    }

    private static void mostrarMenuCategorias() {
        int opcion;
    
        do {
            System.out.println("----- Menú de Categorías -----");
            System.out.println("1. Crear Categoría");
            System.out.println("2. Modificar Categoría");
            System.out.println("3. Eliminar Categoría");
            System.out.println("4. Volver al Menú Principal");
            System.out.print("Selecciona una opción (1-4): ");
    
            opcion = entrada.nextInt();
            entrada.nextLine();  // Limpiar el buffer de entrada
    
            switch (opcion) {
                case 1:
                    crearCategoria();
                    break;
                case 2:
                    modificarCategoria();
                    break;
                case 3:
                    eliminarCategoria();
                    break;
                case 4:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
            }
    
        } while (opcion != 4);
    }

    private static void crearCategoria() {
        System.out.print("Nombre de la categoría: ");
        String nombreCategoria = entrada.nextLine();
    
        // Verificar si ya existe una categoría con ese nombre
        if (existeCategoria(nombreCategoria)) {
            System.out.println("¡La categoría ya existe!");
            return;
        }
    
        System.out.print("Icono: ");
        String iconoCategoria = entrada.nextLine();
        System.out.print("Color: ");
        String colorCategoria = entrada.nextLine();
    
        Categoria nuevaCategoria = new Categoria(nombreCategoria, iconoCategoria, colorCategoria);
        categorias.add(nuevaCategoria);
        System.out.println("Categoría creada con éxito.");
    }
    
    private static boolean existeCategoria(String nombre) {
        for (Categoria categoria : categorias) {
            if (categoria.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }    
    
    private static void modificarCategoria() {
        System.out.print("Ingrese el nombre de la categoría a modificar: ");
        String nombreCategoria = entrada.nextLine();
    
        Categoria categoriaExistente = obtenerCategoriaPorNombre(nombreCategoria);
        if (categoriaExistente == null) {
            System.out.println("¡La categoría no existe!");
            return;
        }
    
        System.out.print("Nuevo nombre (deje en blanco para no modificar): ");
        String nuevoNombre = entrada.nextLine();
        System.out.print("Nuevo icono (deje en blanco para no modificar): ");
        String nuevoIcono = entrada.nextLine();
        System.out.print("Nuevo color (deje en blanco para no modificar): ");
        String nuevoColor = entrada.nextLine();
    
        if (!nuevoNombre.isEmpty()) {
            if (existeCategoria(nuevoNombre)) {
                System.out.println("¡Ya existe una categoría con ese nombre!");
                return;
            }
            categoriaExistente.setNombre(nuevoNombre);
        }
    
        if (!nuevoIcono.isEmpty()) {
            categoriaExistente.setIcono(nuevoIcono);
        }
    
        if (!nuevoColor.isEmpty()) {
            categoriaExistente.setColor(nuevoColor);
        }
    
        System.out.println("Categoría modificada con éxito.");
    }    

    private static Categoria obtenerCategoriaPorNombre(String nombre) {
        for (Categoria categoria : categorias) {
            if (categoria.getNombre().equalsIgnoreCase(nombre)) {
                return categoria;
            }
        }
        return null;
    }    

    private static void eliminarCategoria() {
        entrada.nextLine();  // Limpiar el buffer de entrada
    
        System.out.print("Ingrese el nombre de la categoría a eliminar: ");
        String nombreCategoria = entrada.nextLine();
    
        Categoria categoriaExistente = obtenerCategoriaPorNombre(nombreCategoria);
        if (categoriaExistente == null) {
            System.out.println("¡La categoría no existe!");
            return;
        }
    
        categorias.remove(categoriaExistente);
        System.out.println("Categoría eliminada con éxito.");
    }
    
    private static void mostrarMenuTransacciones() {
        int opcion;
    
        do {
            System.out.println("----- Menú de Transacciones -----");
            System.out.println("1. Ver Todos los Registros");
            System.out.println("2. Ver Ingresos");
            System.out.println("3. Ver Egresos");
            System.out.println("4. Ver Transferencias");
            System.out.println("5. Ver Registros por Fecha");

            System.out.println("6. Volver al Menú Principal");
            System.out.print("Selecciona una opción (1-6): ");
    
            opcion = entrada.nextInt();
            entrada.nextLine();  // Limpiar el buffer de entrada
    
            switch (opcion) {
                case 1:
                    mostrarTodosLosRegistros();
                    break;
                case 2:
                    mostrarRegistrosPorTipo("Ingreso");
                    break;
                case 3:
                    mostrarRegistrosPorTipo("Egreso");
                    break;
                case 4:
                    mostrarRegistrosPorTipo("Transacción");
                    break;
                case 5:
                    mostrarRegistrosPorFecha();
                    break;                
                case 6:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
            }
    
        } while (opcion != 6);
    }
    
    private static void mostrarTodosLosRegistros() {
        if (registros.isEmpty()) {
            System.out.println("No hay registros aún.");
        } else {
            System.out.println("----- Todos los Registros -----");
            for (Registro registro : registros) {
                System.out.println(registro);
            }
        }
    }
    
    private static void mostrarRegistrosPorTipo(String tipo) {
        if (registros.isEmpty()) {
            System.out.println("No hay registros aún.");
        } else {
            System.out.println("----- " + tipo + " -----");
            for (Registro registro : registros) {
                if (registro.getClass().getSimpleName().equals(tipo)) {
                    System.out.println(registro);
                }
            }
        }
    }    

    private static void mostrarRegistrosPorFecha() {
        System.out.print("Ingrese la fecha (DD/MM/AAAA): ");
        String fechaInput = entrada.nextLine();

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaBusqueda = formatter.parse(fechaInput);

            if (registros.isEmpty()) {
                System.out.println("No hay registros aún.");
                return;
            }

            boolean registrosEncontrados = false;

            System.out.println("----- Registros del " + fechaInput + " -----");
            for (Registro registro : registros) {
                Date fechaRegistro = quitarTiempo(registro.getFecha()); // Método auxiliar para quitar la parte de tiempo
                fechaBusqueda = quitarTiempo(fechaBusqueda); // Asegurar que la fecha de búsqueda no tenga parte de tiempo
                if (fechaRegistro != null && fechaRegistro.compareTo(fechaBusqueda) == 0) {
                    System.out.println(registro);
                    registrosEncontrados = true;
                }
            }

            if (!registrosEncontrados) {
                System.out.println("No se encontraron registros para la fecha " + fechaInput);
            }
        } catch (ParseException e) {
            System.out.println("Formato de fecha incorrecto. Por favor, usa el formato DD/MM/AAAA.");
        }
    }

    private static Date quitarTiempo(Date fecha) {
        if (fecha == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
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
        Categoria categoria;
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

            Date fecha = new Date();  // Fecha actual por defecto

            switch (tipoTransaccion) {
                case 1:
                    categoria = seleccionarCategoria();
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
                    categoria = seleccionarCategoria();
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

    private static Categoria seleccionarCategoria() {
        if (categorias.isEmpty()) {
            System.out.println("No hay categorías disponibles. Se asignará 'Sin Categoría'.");
            return new Categoria("Sin Categoría", "", "");
        }
    
        System.out.println("Categorías disponibles:");
        for (int i = 0; i < categorias.size(); i++) {
            System.out.println((i + 1) + ". " + categorias.get(i).getNombre());
        }
    
        System.out.print("Seleccione una categoría (0 para Sin Categoría): ");
        int opcionCategoria = entrada.nextInt();
        entrada.nextLine();  // Limpiar el buffer de entrada
    
        if (opcionCategoria == 0 || opcionCategoria > categorias.size()) {
            System.out.println("Se asignará 'Sin Categoría'.");
            return new Categoria("Sin Categoría", "", "");
        }
    
        return categorias.get(opcionCategoria - 1);
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
    
}
