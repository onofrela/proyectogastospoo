package facade;

import java.util.List;
import java.util.Scanner;

import categoria.Categoria;

public class GestorCategorias {
    private List<Categoria> categorias;
    private Scanner entrada;
    
    public GestorCategorias(List<Categoria> categorias, Scanner entrada){
        this.categorias = categorias;
        this.entrada = entrada;
    }

    public void crearCategoria() {
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
    
    public boolean existeCategoria(String nombre) {
        for (Categoria categoria : categorias) {
            if (categoria.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }    
    
    public void modificarCategoria() {
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

    public Categoria obtenerCategoriaPorNombre(String nombre) {
        for (Categoria categoria : categorias) {
            if (categoria.getNombre().equalsIgnoreCase(nombre)) {
                return categoria;
            }
        }
        return null;
    }    

    public void eliminarCategoria() {
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

    public void enlistarCategorias(){
        for (int i = 0; i < categorias.size(); i++) {
            System.out.println((i + 1) + ". " + categorias.get(i).getNombre());
        }
    }
        
    public Categoria seleccionarCategoria() {
        if (categorias.isEmpty()) {
            System.out.println("No hay categorías disponibles. Se asignará 'Sin Categoría'.");
            return new Categoria("Sin Categoría", "", "");
        }
    
        System.out.println("Categorías disponibles:");
        this.enlistarCategorias();
    
        System.out.print("Seleccione una categoría (0 para Sin Categoría): ");
        int opcionCategoria = entrada.nextInt();
        entrada.nextLine();  // Limpiar el buffer de entrada
    
        if (opcionCategoria == 0 || opcionCategoria > categorias.size()) {
            System.out.println("Se asignará 'Sin Categoría'.");
            return new Categoria("Sin Categoría", "", "");
        }
    
        return categorias.get(opcionCategoria - 1);
    }

}
