package org.manuel.servicio;

import org.manuel.modelo.Categoria;
import org.manuel.modelo.Producto;
import org.manuel.repositorio.CategoriaImpl;
import org.manuel.repositorio.IRepositorio;
import org.manuel.repositorio.RepositorioImpl;
import org.manuel.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ServicioImpl implements IServicio{

    private IRepositorio<Producto> productoRepositorio;
    private IRepositorio<Categoria> categoriaRepositorio;

    public ServicioImpl() {
        this.productoRepositorio = new RepositorioImpl();
        this.categoriaRepositorio = new CategoriaImpl();
    }

    @Override
    public List<Producto> listar() throws SQLException {
        try(Connection conn = ConexionBaseDatos.getConnection()){
            productoRepositorio.setConn(conn);
            return productoRepositorio.findAll();
        }
    }

    @Override
    public Producto porId(Long id) throws SQLException {
        try(Connection conn = ConexionBaseDatos.getConnection()){
            productoRepositorio.setConn(conn);
            return productoRepositorio.findById(id);
        }
    }

    @Override
    public Producto guardar(Producto producto) throws SQLException {
        try(Connection conn = ConexionBaseDatos.getConnection()){
            productoRepositorio.setConn(conn);
            if(conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            try{
                producto = productoRepositorio.save(producto);
                conn.commit();
            }catch (SQLException e){
                e.printStackTrace();
                conn.rollback();
            }
            return producto;
        }
    }

    @Override
    public void editar(Long id, Producto producto) throws SQLException {
        try(Connection conn = ConexionBaseDatos.getConnection()){
            productoRepositorio.setConn(conn);
            if(conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            try{
                productoRepositorio.save(producto);
                conn.commit();
            }catch (SQLException e){
                e.printStackTrace();
                conn.rollback();
            }
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        try(Connection conn = ConexionBaseDatos.getConnection()){
            productoRepositorio.setConn(conn);
            if(conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            try{
                productoRepositorio.delete(id);
                conn.commit();
            }catch (SQLException e){
                e.printStackTrace();
                conn.rollback();
            }
        }
    }

    @Override
    public void guardarProductoConCategoria(Producto producto, Categoria categoria) throws SQLException {
        try(Connection conn = ConexionBaseDatos.getConnection()){
            categoriaRepositorio.setConn(conn);
            productoRepositorio.setConn(conn);
            if(conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            try{
                categoria = categoriaRepositorio.save(categoria);
                producto.setCategoria(categoria);
                productoRepositorio.save(producto);
                conn.commit();
            }catch (SQLException e){
                e.printStackTrace();
                conn.rollback();
            }
        }
    }

    @Override
    public List<Categoria> listarCategoria() throws SQLException {
        try(Connection conn = ConexionBaseDatos.getConnection()){
            categoriaRepositorio.setConn(conn);
            return categoriaRepositorio.findAll();
        }

    }

    @Override
    public Categoria categoriaPorId(Long id) throws SQLException {
        try(Connection conn = ConexionBaseDatos.getConnection()){
            categoriaRepositorio.setConn(conn);
            return categoriaRepositorio.findById(id);
        }
    }

    @Override
    public Categoria guardarCategoria(Categoria categoria) throws SQLException {
        try(Connection conn = ConexionBaseDatos.getConnection()){
            categoriaRepositorio.setConn(conn);
            if(conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            try{
                conn.commit();
                categoria = categoriaRepositorio.save(categoria);
            }catch (SQLException e){
                e.printStackTrace();
                conn.rollback();
            }
            return categoria;
        }
    }

    @Override
    public void editarCategoria(Long id, Categoria categoria) throws SQLException {
        try(Connection conn = ConexionBaseDatos.getConnection()){
            categoriaRepositorio.setConn(conn);
            if(conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            try{
                conn.commit();
                categoriaRepositorio.modify(id, categoria);
            }catch (SQLException e){
                e.printStackTrace();
                conn.rollback();
            }
        }
    }

    @Override
    public void eliminarCategoria(Long id) throws SQLException {
        try(Connection conn = ConexionBaseDatos.getConnection()){
            categoriaRepositorio.setConn(conn);
            if(conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            try{
                conn.commit();
                categoriaRepositorio.delete(id);
            }catch (SQLException e){
                e.printStackTrace();
                conn.rollback();
            }
        }
    }
}
