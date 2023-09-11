package ru.aston.ian_ev.task4.dao;

import java.util.List;

public interface DaoEntityLayer<T> {
    List<T> findAll();
    T findEntityById(int id);
    boolean delete(int id);
    boolean create(T t);
    T update(T t);
}
