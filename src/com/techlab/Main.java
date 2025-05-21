package com.techlab;


import com.techlab.productos.Producto;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        ArrayList<Producto> productos = new ArrayList<>();
        int opcionUsuario = 0;

        while (opcionUsuario != 7){

            System.out.print("""
                ===== SISTEMA DE GESTIÓN  =====
                1) Agregar producto
                2) Listar productos
                3) Buscar/Actualizar producto
                4) Eliminar producto
                5) Crear un pedido
                6) Listar pedidos
                7) Salir
                
                Elija una opcion: """);

            opcionUsuario = entrada.nextInt();

            switch (opcionUsuario){
                case 1 ->  agregarProducto(productos);
                case 2 -> listarProductos(productos);
                case 3 -> buscarActualizarProducto(productos);
                case 4 -> eliminarProducto(productos);
                case 5 -> System.out.println("Crear un pedido");
                case 6 -> System.out.println("Listar pedidos");
                case 7 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    // AGREGAR PRODUCTOS
    private static void agregarProducto(ArrayList<Producto> productos) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("===== Nuevo Producto =====");
        System.out.print("Nombre:");
        String nombre = entrada.nextLine();
        System.out.print("Precio:");
        double precio = entrada.nextDouble();
        System.out.print("Stock:");
        int stock = entrada.nextInt();
        Producto producto = new Producto(nombre,precio,stock);

        productos.add(producto);
        System.out.println("Producto Agregado.");
    }

    // LISTAR PRODUCTOS
    private static void listarProductos(ArrayList<Producto> productos){
        if (productos.isEmpty()){
            System.out.println("No hay Productos cargados");
        } else {
            System.out.println(" ===== Lista de Productos =====");
            for (Producto producto:productos){
                producto.mostrarInfo();
            }
        }
    }

    // BUSCAR / ACTUALIZAR PRODUCTO
    private static void buscarActualizarProducto(ArrayList<Producto> productos) {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Ingrese el ID del producto:");
        int id = entrada.nextInt();

        for (Producto producto : productos) {
            if (producto.getId() == id) {
                System.out.println("Producto encontrado:");
                producto.mostrarInfo();

                System.out.println("¿Qué desea actualizar? (1: Precio, 2: Stock)");
                int opcion = entrada.nextInt();
                if (opcion == 1) {
                    System.out.print("Nuevo precio: ");
                    double nuevoPrecio = entrada.nextDouble();
                    producto.setPrecio(nuevoPrecio);
                    System.out.println("Precio actualizado.");
                } else if (opcion == 2) {
                    System.out.print("Nuevo stock: ");
                    int nuevoStock = entrada.nextInt();
                    producto.setStock(nuevoStock);
                    System.out.println("Stock actualizado.");
                }
                return;
            }
        }
        System.out.println("Producto no encontrado.");
    }

    // ELIMINAR PRODUCTO
    private static void eliminarProducto(ArrayList<Producto> productos) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingrese el ID del producto a eliminar: ");
        int id = entrada.nextInt();

        for (Producto producto : productos) {
            if (producto.getId() == id) {
                producto.mostrarInfo();
                productos.remove(producto);
                System.out.println("Producto eliminado.");
                return;
            }
        }
        System.out.println("No existe el producto con el ID ingresado");
    }
}