package datos;

import cuentas.Cuenta;
import registros.Registro;
import java.io.*;
import java.util.List;

public class ManejoArchivos {
    public static void guardarDatos(List<Registro> registros, List<Cuenta> cuentas) {
        try (FileOutputStream fileOut = new FileOutputStream("datos/datos_registros.ser");
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            objectOut.writeObject(registros);
            objectOut.writeObject(cuentas);

            System.out.println("Datos guardados exitosamente en archivos.");
        } catch(FileNotFoundException e) {
            System.out.println("Primera vez utilizando el programa");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cargarDatos(List<Registro> registros, List<Cuenta> cuentas) {
        try (FileInputStream fileIn = new FileInputStream("datos/datos_registros.ser");
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            registros.addAll((List<Registro>) objectIn.readObject());
            cuentas.addAll((List<Cuenta>) objectIn.readObject());

            System.out.println("Datos cargados exitosamente desde archivos.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
