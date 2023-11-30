package datos;

import cuentas.Cuenta;
import registros.Registro;
import java.io.*;
import java.util.List;

import categoria.Categoria;

public class ManejoArchivos {
    public static void guardarDatos(List<Registro> registros, List<Cuenta> cuentas, List<Categoria> categorias) {
        try (FileOutputStream fileOut = new FileOutputStream("datos/datos_registros.ser");
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            objectOut.writeObject(registros);
            objectOut.writeObject(cuentas);
            objectOut.writeObject(categorias);

            System.out.println("Datos guardados exitosamente en archivos.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cargarDatos(List<Registro> registros, List<Cuenta> cuentas, List<Categoria> categorias) {
        try (FileInputStream fileIn = new FileInputStream("datos/datos_registros.ser");
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            registros.addAll((List<Registro>) objectIn.readObject());
            cuentas.addAll((List<Cuenta>) objectIn.readObject());

            if (objectIn.available() > 0) {
                categorias.addAll((List<Categoria>) objectIn.readObject());
            }

            System.out.println("Datos cargados exitosamente desde archivos.");
        } catch (FileNotFoundException e) {
            System.out.println("Primera vez utilizando el programa");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
