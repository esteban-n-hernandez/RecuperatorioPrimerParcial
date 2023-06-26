package ar.edu.usal.programacion.dao.impl;

import ar.edu.usal.programacion.dao.ArticuloDAO;
import ar.edu.usal.programacion.domain.Articulo;
import ar.edu.usal.programacion.domain.Vendedor;
import ar.edu.usal.programacion.util.FileUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ArticuloDAOImpl implements ArticuloDAO {

    private static final String ARTICULOS_FILE = "articulos.txt";

    @Override
    public void crear(Articulo articulo) throws IOException {
        List<String> lines = new ArrayList<>();
        String filepath = cargarProperties();
        lines.add(articulo.toStringd());
        FileUtil.guardar(filepath, lines, "Articulo");
    }

    @Override
    public Articulo obtenerPorID(Integer id) {
        try {
            List<String> lines = FileUtil.readLines(ARTICULOS_FILE);
            for (String line : lines) {
                Articulo articulo = parseArticulo(line);
                if (articulo.getNombre().equals(id)) {
                    return articulo;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de artículos: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void actualizar(Articulo articulo) throws IOException {
        List<Articulo> articulos = obtenerTodos();
        for (int i = 0; i < articulos.size(); i++) {
            if (articulos.get(i).getId() == articulo.getId()) {
                articulos.set(i, articulo);
                break;
            }
        }
        List<String> lines = new ArrayList<>();
        for (Articulo a : articulos) {
            lines.add(a.toString());
        }
        try {
            FileUtil.writeLines(ARTICULOS_FILE, lines);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo de artículos: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(Articulo articulo) throws IOException {
        List<Articulo> articulos = obtenerTodos();
        articulos.removeIf(a -> a.getId() == articulo.getId());
        List<String> lines = new ArrayList<>();
        for (Articulo a : articulos) {
            lines.add(a.toString());
        }
        try {
            FileUtil.writeLines(ARTICULOS_FILE, lines);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo de artículos: " + e.getMessage());
        }
    }

    @Override
    public List<Articulo> obtenerTodos() throws IOException {
        List<Articulo> articulos = new ArrayList<>();
        Properties properties = new Properties();
        properties.load(new FileReader("config.properties"));
        String filePath = properties.getProperty("ruta.ficheros.articulos");
        try {
            List<String> lines = FileUtil.readLines(filePath);
            for (String line : lines) {
                articulos.add(parseArticulo(line));
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de artículos: " + e.getMessage());
        }
        return articulos;
    }

    private Articulo parseArticulo(String line) throws IOException {
        String[] lines = line.split("\n");
        int id = 0;
        String nombre = "";
        String descripcion = "";
        String categoria = "";
        int cantidad = 0;
        double precio = 0.0;
        List<Vendedor> vendedores = new ArrayList<>();

        for (String currentLine : lines) {
            String[] keyValue = currentLine.split(":");
            if (keyValue.length != 2) {
                throw new IOException("Formato de línea incorrecto: " + currentLine);
            }
            String key = keyValue[0].trim();
            String value = keyValue[1].trim();

            switch (key) {
                case "Artículo ID":
                    id = Integer.parseInt(value);
                    break;
                case "Nombre":
                    nombre = value;
                    break;
                case "Descripción":
                    descripcion = value;
                    break;
                case "Categoría":
                    categoria = value;
                    break;
                case "Cantidad":
                    cantidad = Integer.parseInt(value);
                    break;
                case "Precio":
                    precio = Double.parseDouble(value);
                    break;
                default:
                    throw new IOException("Clave desconocida: " + key);
            }
        }
        return new Articulo(id, nombre, descripcion, categoria, cantidad, precio, vendedores);
    }

    private List<Vendedor> parseVendedores(String vendedoresData) throws IOException {
        List<Vendedor> vendedores = new ArrayList<>();
        String[] vendedorArray = vendedoresData.split(";");
        for (String vendedorData : vendedorArray) {
            String[] datos = vendedorData.split(",");
            String nombre = datos[0];
            String apellido = datos[1];
            int id = Integer.parseInt(datos[2]);
            // Aquí necesitarás obtener la lista de artículos correspondiente al vendedor
            List<Articulo> articulos = obtenerArticulosPorVendedor(id);
            Vendedor vendedor = new Vendedor(nombre, apellido, id);
            vendedores.add(vendedor);
        }
        return vendedores;
    }

    private List<Articulo> obtenerArticulosPorVendedor(int vendedorId) throws IOException {
        List<Articulo> articulos = new ArrayList<>();

        // Obtener todos los artículos desde el ArticuloDAO
        ArticuloDAO articuloDAO = new ArticuloDAOImpl(); // Reemplaza con tu implementación del DAO
        List<Articulo> todosLosArticulos = articuloDAO.obtenerTodos();

        // Filtrar los artículos por vendedor
        for (Articulo articulo : todosLosArticulos) {
            List<Vendedor> vendedores = articulo.getVendedores();
            for (Vendedor vendedor : vendedores) {
                if (vendedor.getCuit() == vendedorId) {
                    articulos.add(articulo);
                    break;
                }
            }
        }

        return articulos;
    }

    private String cargarProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("config.properties"));
        return properties.getProperty("ruta.ficheros.articulos");
    }

}


