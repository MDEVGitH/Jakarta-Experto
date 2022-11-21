package org.manuel.repositorio;

import org.manuel.modelo.Categoria;
import org.manuel.modelo.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioImpl implements IRepositorio<Producto>{

    private Connection conn;

    public RepositorioImpl(Connection conn) {
        this.conn = conn;
    }

    public RepositorioImpl() {
    }


    public void setConn(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List findAll() throws SQLException {
        List<Producto> productos= new ArrayList<>();
        try(
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT p.*, c.nombre as categoria FROM productos AS p " +
                        "INNER JOIN categorias AS c ON (p.id_categoria = c.id)")){
            while (rs.next()){
                Producto producto = mapearProducto(rs);
                productos.add(producto);
            }
        }
        return productos;
    }

    @Override
    public Producto findById(long id) throws SQLException {
        Producto producto = null;
        try(
                PreparedStatement stmt = conn.
                prepareStatement("SELECT p.*, c.nombre as categoria FROM productos AS p " +
                        "INNER JOIN categorias AS c ON (p.id_categoria = c.id) WHERE p.id = ?")){
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    producto = mapearProducto(rs);
                }
            }
        }
        return producto;
    }


    @Override
    public Producto save(Producto producto) throws SQLException {
        String sql = "INSERT INTO productos(nombre, precio, fecha_registro, id_categoria, sku) VALUES (?,?,?,?,?)";
        try(
                PreparedStatement stmt = conn.
                prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, producto.getNombre());
            stmt.setBigDecimal(2, producto.getPrecio());
            stmt.setDate(3, new Date(producto.getDate().getTime()));
            stmt.setLong(4, producto.getCategoria().getId());
            stmt.setString(5, producto.getSku());
            stmt.executeUpdate();

            try(ResultSet rs = stmt.getGeneratedKeys()){
                if(rs.next()){
                    producto.setId(rs.getLong(1));
                }
            }
        }
        return producto;
    }

    @Override
    public void modify(long id, Producto producto) throws SQLException {
        String sql = "UPDATE productos SET nombre = ?, precio = ?, fecha_registro = ?, id_categoria = ?, sku = ? WHERE id = ?";
        try(
                PreparedStatement stmt = conn.
                prepareStatement(sql)){
            stmt.setString(1, producto.getNombre());
            stmt.setBigDecimal(2, producto.getPrecio());
            stmt.setDate(3, new Date(producto.getDate().getTime()));
            stmt.setLong(4, producto.getCategoria().getId());
            stmt.setString(5, producto.getSku());
            stmt.setLong(6, id);
            stmt.executeUpdate();
        }
    }


    @Override
    public void delete(long id) throws SQLException {
        try(
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM productos WHERE id = ?")){
            stmt.setLong(1, id);
            stmt.executeUpdate();
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
        producto.setSku(rs.getString("sku"));
        return producto;
    }
}
