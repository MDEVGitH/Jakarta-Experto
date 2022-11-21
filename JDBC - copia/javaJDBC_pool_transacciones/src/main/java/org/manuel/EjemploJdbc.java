package org.manuel;

import org.manuel.modelo.Categoria;
import org.manuel.modelo.Producto;
import org.manuel.repositorio.CategoriaImpl;
import org.manuel.repositorio.IRepositorio;
import org.manuel.repositorio.RepositorioImpl;
import org.manuel.servicio.IServicio;
import org.manuel.servicio.ServicioImpl;
import org.manuel.util.ConexionBaseDatos;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class EjemploJdbc {
    public static void main(String[] args) throws SQLException {
        IServicio servicio = new ServicioImpl();
        System.out.println("============== ICATEGORIAS ANTES DE ==============");
        servicio.listarCategoria().forEach(System.out::println);
        System.out.println("============== PRODUCTOS ANTES DE ==============");
        servicio.listar().forEach(System.out::println);
        System.out.println("============== INSERTAR NUEVO PRODUCTO CON CATEGORIA ==============");
        Producto producto = new Producto();
        producto.setNombre("Zapatos Nike2");
        producto.setPrecio(BigDecimal.valueOf(280900));
        producto.setDate(new Date());
        producto.setSku("abcd12354");

        Categoria categoria = new Categoria();
        categoria.setNombre("Ropa");

        servicio.guardarProductoConCategoria(producto, categoria);
        System.out.println("============== ICATEGORIAS DESPUES DE ==============");
        servicio.listarCategoria().forEach(System.out::println);
        System.out.println("============== PRODUCTOS DESPUES DE ==============");
        servicio.listar().forEach(System.out::println);



    }
}