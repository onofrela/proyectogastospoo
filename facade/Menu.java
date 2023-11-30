package facade;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import categoria.Categoria;
import cuentas.Cuenta;
import registros.Registro;
import datos.ManejoArchivos;

public class Menu {
    private static Scanner entrada = new Scanner(System.in);

    private static List<Registro> registros = new ArrayList<>();
    private static List<Cuenta> cuentas = new ArrayList<>();
    private static List<Categoria> categorias = new ArrayList<>();

    private static GestorCuentas gestorCuentas;
    private static GestorCategorias gestorCategorias;
    private static GestorRegistros gestorRegistros;

    public static void Principal() {
        int opcion;
        ManejoArchivos.cargarDatos(registros, cuentas, categorias);
        
        gestorCuentas = new GestorCuentas(cuentas, entrada);
        gestorCategorias = new GestorCategorias(categorias, entrada);
        gestorRegistros = new GestorRegistros(cuentas, registros, gestorCategorias, gestorCuentas, entrada);

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
                    gestorRegistros.agregarRegistro();
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
            System.out.println("4. Ver Categorías");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Selecciona una opción (1-5): ");
    
            opcion = entrada.nextInt();
            entrada.nextLine();  // Limpiar el buffer de entrada
    
            switch (opcion) {
                case 1:
                    gestorCategorias.crearCategoria();
                    break;
                case 2:
                    gestorCategorias.modificarCategoria();
                    break;
                case 3:
                    gestorCategorias.eliminarCategoria();
                    break;
                case 4:
                    if(!categorias.isEmpty())
                        gestorCategorias.enlistarCategorias();
                    else
                        System.out.println("Sin categorías");
                case 5:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
            }
    
        } while (opcion != 5);
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
                    gestorRegistros.mostrarTodosLosRegistros();
                    break;
                case 2:
                    gestorRegistros.mostrarRegistrosPorTipo("Ingreso");
                    break;
                case 3:
                    gestorRegistros.mostrarRegistrosPorTipo("Egreso");
                    break;
                case 4:
                    gestorRegistros.mostrarRegistrosPorTipo("Transacción");
                    break;
                case 5:
                    gestorRegistros.mostrarRegistrosPorFecha();
                    break;                
                case 6:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
            }
    
        } while (opcion != 6);
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
                    gestorCuentas.mostrarCuentas();
                    break;
                case 2:
                    gestorCuentas.agregarCuenta();
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
    
    private static void generarReportes() {
        // Lógica para generar reportes
    }
}
