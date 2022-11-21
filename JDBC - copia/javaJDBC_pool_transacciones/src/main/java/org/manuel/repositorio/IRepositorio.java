package org.manuel.repositorio;

import org.manuel.modelo.Producto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IRepositorio <T>{
    List<T> findAll() throws SQLException;
    T findById(long id) throws SQLException;
    T save(T t) throws SQLException;
    void modify(long id, T t) throws SQLException;
    void delete(long id) throws SQLException;
    void setConn(Connection conn);
}
