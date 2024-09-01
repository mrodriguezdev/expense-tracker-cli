package com.mrodriguezdev.service;

import com.mrodriguezdev.model.Expense;
import com.mrodriguezdev.model.ExpenseWrapper;
import com.mrodriguezdev.repository.Repository;

import java.time.LocalDateTime;
import java.util.List;

public class ExpenseTrackerServiceImpl implements ExpenseTrackerService {
    private final Repository<ExpenseWrapper> repository;

    public ExpenseTrackerServiceImpl(Repository<ExpenseWrapper> repository) {
        this.repository = repository;
    }

    @Override
    public Expense add(String description, Double amount) {
        Expense expense = new Expense(repository.generateNewId(), description, amount, LocalDateTime.now());
        ExpenseWrapper expenseWrapper = repository.loadExpenses();
        expenseWrapper.getExpenses().add(expense);
        repository.saveExpenses(expenseWrapper);
        repository.saveNewId(expense.getId());
        return expense;
    }


    @Override
    public void update(Long id, String description, Double amount) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Expense> list() {
        return null;
    }

    @Override
    public Double summary() {
        return null;
    }

    @Override
    public Double summaryOf(int month) {
        return null;
    }
}
