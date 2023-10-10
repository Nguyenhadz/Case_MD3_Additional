package com.example.bai_thi_thuc_hanh_md03.service;

import java.sql.SQLException;
import java.util.List;

public interface GenericService<T> {
    List<T> findAll();
    void create(T t) throws SQLException;
    boolean update(T t) throws SQLException;
    boolean delete(int id) throws SQLException;
    List<T> findByName(String name);
    T findById(int id);
}
