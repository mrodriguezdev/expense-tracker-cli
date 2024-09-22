package com.mrodriguezdev.repository;

import com.mrodriguezdev.model.Expense;

import java.util.List;

public interface ExpenseRepository {
    Expense save(String description, Double amount);
    void update(Expense expense);
    Expense findById(Long id);
    void delete(Expense expense);
    List<Expense> listAll();
    void exportToCsv(List<Expense> expenses);
}
