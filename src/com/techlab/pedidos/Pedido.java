package com.techlab.pedidos;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int contadorId = 1;
    private int id;
    private List<LineaPedido> lineas;
    private double total;

    public Pedido() {
        this.id = contadorId++;
        this.lineas = new ArrayList<>();
        this.total = 0.0;
    }

    public void agregarLinea(LineaPedido linea) {
        lineas.add(linea);
        total += linea.getSubtotal();
    }

    public double getTotal() {
        return total;
    }

    public void mostrarPedido() {
        System.out.println("===== Pedido ID: " + id + " =====");
        for (LineaPedido linea : lineas) {
            System.out.printf("Producto: %s | Cantidad: %d | Subtotal: %.2f\n",
                    linea.getProducto().getNombre(),
                    linea.getCantidad(),
                    linea.getSubtotal());
        }
        System.out.printf("TOTAL: %.2f\n", total);
    }
}
