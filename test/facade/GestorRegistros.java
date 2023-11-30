package test.facade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

public class GestorRegistros {
    private List<Cuenta> cuentas;
    private List<Registro> registros;
    private GestorCategorias gestorCategorias;
    private GestorCuentas gestorCuentas;
    private Scanner entrada;
    
    public GestorRegistros(List<Cuenta> cuentas, List<Registro> registros, GestorCategorias gestorCategorias, GestorCuentas gestorCuentas, Scanner entrada){
        this.cuentas = cuentas;
        this.registros = registros;
        this.gestorCuentas = gestorCuentas;
        this.gestorCategorias = gestorCategorias;
        this.entrada = entrada;
    }
    
    public GestorRegistros(List<Cuenta> cuentas, List<Registro> registros, GestorCategorias gestorCategorias, GestorCuentas gestorCuentas){
        this.cuentas = cuentas;
        this.registros = registros;
        this.gestorCuentas = gestorCuentas;
        this.gestorCategorias = gestorCategorias;
        this.entrada = new Scanner(System.in);
    }

    public void mostrarTodosLosRegistros() {
        if (registros.isEmpty()) {
            System.out.println("No hay registros aún.");
        } else {
            System.out.println("----- Todos los Registros -----");
            for (Registro registro : registros) {
                System.out.println(registro);
            }
        }
    }
    
    public void mostrarRegistrosPorTipo(String tipo) {
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

    public void mostrarRegistrosPorFecha() {
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

    public Date quitarTiempo(Date fecha) {
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

    public void agregarRegistro() {
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
                    categoria = gestorCategorias.seleccionarCategoria();
                    gestorCuentas.mostrarCuentas();
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
                    categoria = gestorCategorias.seleccionarCategoria();
                    gestorCuentas.mostrarCuentas();
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
                    gestorCuentas.mostrarCuentas();
                    System.out.print("Cuenta de origen (índice): ");
                    int indexCuentaOrigen = entrada.nextInt();
                    entrada.nextLine();  // Limpiar el buffer de entrada
                    if (indexCuentaOrigen >= 0 && indexCuentaOrigen < cuentas.size()) {
                        Cuenta cuentaOrigen = cuentas.get(indexCuentaOrigen);
                
                        gestorCuentas.mostrarCuentas();
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

}
