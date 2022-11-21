package org.manuel;

import org.manuel.modelo.Categoria;
import org.manuel.modelo.Producto;
import org.manuel.repositorio.IRepositorio;
import org.manuel.repositorio.RepositorioImpl;
import org.manuel.util.ConexionBaseDatos;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Date;

public class EjemploJdbc {
    public static void main (String[] args){

        try (Connection conn = ConexionBaseDatos.getInstance()){
            IRepositorio<Producto> repositorio = new RepositorioImpl();
            repositorio.findAll().forEach(System.out::println);

            System.out.println(repositorio.findById(1l));

            Producto guardarP = new Producto();

            guardarP.setNombre("Teclado Ryzen");
            guardarP.setPrecio(BigDecimal.valueOf(190000));
            guardarP.setDate(new Date());
            Categoria categoria = new Categoria();
            categoria.setId(3L);
            guardarP.setCategoria(categoria);
            repositorio.modify(4L, guardarP);
            System.out.println("Producto Guardado con exito");

            repositorio.findAll().forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}