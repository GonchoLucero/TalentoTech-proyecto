package com.techlab;


import com.techlab.pedidos.LineaPedido;
import com.techlab.pedidos.Pedido;
import com.techlab.productos.Producto;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        ArrayList<Producto> productos = new ArrayList<>();
        ArrayList<Pedido> pedidos = new ArrayList<>();

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
                case 5 -> crearPedido(productos, pedidos);
                case 6 -> listarPedidos(pedidos);
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

    // CREAR PEDIDOS
    private static void crearPedido(ArrayList<Producto> productos, ArrayList<Pedido> pedidos) {
        Scanner entrada = new Scanner(System.in);
        Pedido nuevoPedido = new Pedido();

        boolean seguirAgregando = true;

        while (seguirAgregando) {
            System.out.print("Ingrese el ID del producto: ");
            int id = entrada.nextInt();

            Producto productoSeleccionado = null;
            for (Producto p : productos) {
                if (p.getId() == id) {
                    productoSeleccionado = p;
                    break;
                }
            }

            if (productoSeleccionado == null) {
                System.out.println("Producto no encontrado.");
                continue;
            }


            System.out.printf("Producto encontrado -> ID: %d | Nombre: %s | Precio: %.2f | Stock disponible: %d\n",
                    productoSeleccionado.getId(),
                    productoSeleccionado.getNombre(),
                    productoSeleccionado.getPrecio(),
                    productoSeleccionado.getStock());

            System.out.print("¿Cuántas unidades desea agregar al pedido?: ");
            int cantidad = entrada.nextInt();

            if (cantidad > productoSeleccionado.getStock()) {
                System.out.println("Stock insuficiente. Pedido cancelado para este producto.");
                continue;
            }

            productoSeleccionado.setStock(productoSeleccionado.getStock() - cantidad);
            nuevoPedido.agregarLinea(new LineaPedido(productoSeleccionado, cantidad));
            System.out.println("Producto agregado al pedido.");


            System.out.print("¿Desea agregar otro producto al pedido? (s/n): ");
            entrada.nextLine();
            String continuar = entrada.nextLine().trim().toLowerCase();
            if (!continuar.equals("s")) {
                seguirAgregando = false;
            }
        }

        if (nuevoPedido.getTotal() > 0) {
            pedidos.add(nuevoPedido);
            System.out.println("✅ Pedido creado exitosamente.");
        } else {
            System.out.println("❌ Pedido vacío. No se guardó.");
        }
    }


    // LISTAR PEDIDOS
    private static void listarPedidos(ArrayList<Pedido> pedidos) {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos realizados.");
            return;
        }

        System.out.println("===== LISTA DE PEDIDOS =====");

        int contador = 1;
        for (Pedido pedido : pedidos) {
            System.out.println("Pedido #" + contador);
            pedido.mostrarPedido();
            System.out.println("------------------------------");
            contador++;
        }
    }
}