package org.manuel.servicio;

import org.manuel.modelo.Categoria;
import org.manuel.modelo.Producto;

import java.sql.SQLException;
import java.util.List;

public interface IServicio {

    List<Producto> listar() throws SQLException;

    Producto porId(Long id) throws SQLException;

    Producto guardar(Producto producto) throws SQLException;

    void editar(Long id, Producto producto) throws SQLException;

    void eliminar(Long id) throws SQLException;

    void guardarProductoConCategoria(Producto producto, Categoria categoria) throws SQLException;

    List<Categoria> listarCategoria() throws SQLException;
    Categoria categoriaPorId(Long id) throws SQLException;
    Categoria guardarCategoria(Categoria categoria) throws SQLException;
    void editarCategoria(Long id, Categoria categoria) throws SQLException;
    void eliminarCategoria(Long id) throws SQLException;

}
