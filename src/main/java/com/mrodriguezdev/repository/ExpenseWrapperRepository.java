package com.mrodriguezdev.repository;

import com.mrodriguezdev.model.ExpenseWrapper;

public interface ExpenseWrapperRepository {
    Long generateNewId();
    ExpenseWrapper loadExpenses();
    void saveExpenses(ExpenseWrapper expenseWrapper);
    void saveNewId(Long newId);
    void exportToCsv(ExpenseWrapper expenseWrapper);
}
