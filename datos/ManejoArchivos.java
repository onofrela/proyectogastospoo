package datos;

import cuentas.Cuenta;
import facade.Configuracion;
import registros.Registro;
import java.io.*;
import java.util.List;

import categoria.Categoria;

public class ManejoArchivos {
    private static String directorio = "datos/datos_registros.ser";
    public static void guardarDatos(List<Registro> registros, List<Cuenta> cuentas, List<Categoria> categorias, Configuracion configuracion) {
        try (FileOutputStream fileOut = new FileOutputStream(directorio);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(configuracion);
            objectOut.writeObject(registros);
            objectOut.writeObject(cuentas);
            objectOut.writeObject(categorias);

            System.out.println("Datos guardados exitosamente en archivos.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked") //Para quitar la advertencia de addAll
    public static void cargarDatos(List<Registro> registros, List<Cuenta> cuentas, List<Categoria> categorias, Configuracion configuracion) {
        try (FileInputStream fileIn = new FileInputStream(directorio);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            Configuracion nuevaConfig = ((Configuracion) objectIn.readObject());

            configuracion.setFormatoMonto(nuevaConfig.getFormatoMonto());
            configuracion.setIdioma(nuevaConfig.getIdioma());
            configuracion.setPais(nuevaConfig.getPais());
            configuracion.setPatron(nuevaConfig.getPatron());

            registros.addAll((List<Registro>) objectIn.readObject());
            cuentas.addAll((List<Cuenta>) objectIn.readObject());
            categorias.addAll((List<Categoria>) objectIn.readObject());

            System.out.println("Datos cargados exitosamente desde archivos.");
        } catch (FileNotFoundException e) {
            System.out.println("Primera vez utilizando el programa");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
