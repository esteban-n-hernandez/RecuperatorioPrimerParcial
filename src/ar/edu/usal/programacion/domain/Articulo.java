package ar.edu.usal.programacion.domain;

import java.util.List;

public class Articulo {
    private int id;
    private String nombre;
    private String descripcion;
    private String categoria;
    private int cantidad;
    private double precio;
    private List<Vendedor> vendedores;

    public Articulo(int id, String nombre, String descripcion, String categoria, int cantidad, double precio, List<Vendedor> vendedores) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.precio = precio;
        this.vendedores = vendedores;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public List<Vendedor> getVendedores() {
        return vendedores;
    }

    public void setVendedores(List<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    @Override
    public String toString() {
        return nombre + "," + descripcion + "," + categoria + "," + cantidad + "," + precio + "," + vendedoresToString();
    }

    private String vendedoresToString() {
        StringBuilder sb = new StringBuilder();
        for (Vendedor vendedor : vendedores) {
            sb.append(vendedor.getRazonSocial()).append(",").append(vendedor.getDomicilio()).append(",").append(vendedor.getCuit()).append(";");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1); // Eliminar el último ";"
        }
        return sb.toString();
    }

    public String toStringd() {
        StringBuilder sb = new StringBuilder();
        sb.append("Artículo ID: ").append(id).append("\n");
        sb.append("Nombre: ").append(nombre).append("\n");
        sb.append("Descripción: ").append(descripcion).append("\n");
        sb.append("Categoría: ").append(categoria).append("\n");
        sb.append("Cantidad: ").append(cantidad).append("\n");
        sb.append("Precio: ").append(precio).append("\n");
        sb.append("Vendedores: \n");
        for (Vendedor vendedor : vendedores) {
            sb.append(vendedor).append("\n");
        }
        return sb.toString();
    }
}

