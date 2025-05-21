package com.techlab.productos;

public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int stock;

    private static int contadorId = 1;

    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.id = contadorId;
        contadorId++;
    }

    // METODOS
    public  void  mostrarInfo(){
        System.out.printf("""
                ID: %s | Nombre: %s | Precio: %s | Stock: %s
                """, this.id, this.nombre, this.precio, this.stock );
    }

    // GETTERS y SETTERS
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public double getPrecio() {
        return precio;
    }
    public int getStock() {
        return stock;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
}
