package org.manuel.repositorio;

import java.util.List;

public interface IRepositorio <T>{
    List<T> findAll();
    T findById(long id);
    void save(T t);
    void modify(long id, T t);
    void delete(long id);
}
