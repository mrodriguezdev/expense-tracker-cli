package com.mrodriguezdev.service;

import com.mrodriguezdev.model.Expense;

import java.util.List;

public interface ExpenseTrackerService {
    Expense add(String description, Double amount);
    void update(Long id, String description, Double amount);
    void delete(Long id);
    List<Expense> list();
    Double summary(boolean export);
    Double summaryOf(boolean export, int month);
}
