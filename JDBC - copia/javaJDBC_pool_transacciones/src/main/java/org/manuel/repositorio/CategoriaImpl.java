package org.manuel.repositorio;

import org.manuel.modelo.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaImpl implements IRepositorio<Categoria>{

    private Connection conn;

    public CategoriaImpl(Connection conn) {
        this.conn = conn;
    }

    public CategoriaImpl() {
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Categoria> findAll() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM categorias")){
            while(rs.next()){
                categorias.add(mapCategoria(rs));
            }
        }
        return categorias;
    }

    @Override
    public Categoria findById(long id) throws SQLException {
        Categoria categoria = null;
        try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM categorias as c WHERE c.id = ?")){
            stmt.setLong(1, id);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()){
                    categoria = mapCategoria(rs);
                }
            }
        }
        return categoria;
    }

    @Override
    public Categoria save(Categoria categoria) throws SQLException {
        String sql = "INSERT INTO categorias(nombre) VALUES (?) ";
        try(PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, categoria.getNombre());
            stmt.executeUpdate();
            try(ResultSet rs = stmt.getGeneratedKeys()){
                if(rs.next()){
                    categoria.setId(rs.getLong(1));
                }
            }
        }
        return categoria;
    }

    @Override
    public void modify(long id, Categoria categoria) throws SQLException {
        String sql = "UPDATE categorias SET nombre = ? WHERE id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, categoria.getNombre());
            stmt.setLong(2, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(long id) throws SQLException {
        String sql = "DELETE FROM categorias WHERE id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private Categoria mapCategoria(ResultSet rs) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setId(rs.getLong(1));
        categoria.setNombre(rs.getString(2));
        return categoria;
    }
}
