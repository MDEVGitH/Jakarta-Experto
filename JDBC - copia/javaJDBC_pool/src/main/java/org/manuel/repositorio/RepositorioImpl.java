package org.manuel.repositorio;

import org.manuel.modelo.Categoria;
import org.manuel.modelo.Producto;
import org.manuel.util.ConexionBaseDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioImpl implements IRepositorio<Producto>{

    private Connection getConnection() throws SQLException {
        return ConexionBaseDatos.getConnection();
    }

    @Override
    public List findAll() {
        List<Producto> productos= new ArrayList<>();
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT p.*, c.nombre as categoria FROM productos AS p " +
                        "INNER JOIN categorias AS c ON (p.id_categoria = c.id)")){
            while (rs.next()){
                Producto producto = mapearProducto(rs);
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }

    @Override
    public Producto findById(long id) {
        Producto producto = null;
        try(    Connection conn = getConnection();
                PreparedStatement stmt = conn.
                prepareStatement("SELECT p.*, c.nombre as categoria FROM productos AS p " +
                        "INNER JOIN categorias AS c ON (p.id_categoria = c.id) WHERE p.id = ?")){
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    producto = mapearProducto(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }


    @Override
    public void save(Producto producto) {
        String sql = "INSERT INTO productos(nombre, precio, fecha_registro, id_categoria) VALUES (?,?,?,?)";
        try(Connection conn = getConnection();
                PreparedStatement stmt = conn.
                prepareStatement(sql)){
            stmt.setString(1, producto.getNombre());
            stmt.setBigDecimal(2, producto.getPrecio());
            stmt.setDate(3, new Date(producto.getDate().getTime()));
            stmt.setLong(4, producto.getCategoria().getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modify(long id, Producto producto) {
        String sql = "UPDATE productos SET nombre = ?, precio = ?, fecha_registro = ?, id_categoria = ? WHERE id = ?";
        try(    Connection conn = getConnection();
                PreparedStatement stmt = conn.
                prepareStatement(sql)){
            stmt.setString(1, producto.getNombre());
            stmt.setBigDecimal(2, producto.getPrecio());
            stmt.setDate(3, new Date(producto.getDate().getTime()));
            stmt.setLong(4, producto.getCategoria().getId());
            stmt.setLong(5, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete(long id) {
        try(Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM productos WHERE id = ?")){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Producto mapearProducto(ResultSet rs) throws SQLException {
        Producto producto = new Producto();
        producto.setId(rs.getLong("id"));
        producto.setNombre(rs.getString("nombre"));
        producto.setPrecio(rs.getBigDecimal("precio"));
        producto.setDate(rs.getDate("fecha_registro"));
        Categoria categoria = new Categoria();
        categoria.setId(rs.getLong("id_categoria"));
        categoria.setNombre(rs.getString("categoria"));
        producto.setCategoria(categoria);
        return producto;
    }
}
