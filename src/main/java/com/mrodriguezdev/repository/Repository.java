package com.mrodriguezdev.repository;

public interface Repository<T> {
    Long generateNewId();
    T loadExpenses();
    void saveExpenses(T t);
    void saveNewId(Long newId);
    void exportToCsv(T t);
}
