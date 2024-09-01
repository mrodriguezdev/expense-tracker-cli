package com.mrodriguezdev.model;

import java.util.ArrayList;
import java.util.List;

public class ExpenseWrapper {
    private List<Expense> expenses;

    public ExpenseWrapper() {
        this.expenses = new ArrayList<>();
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }
}
