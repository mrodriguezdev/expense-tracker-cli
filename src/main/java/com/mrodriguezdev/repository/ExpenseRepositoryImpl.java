package com.mrodriguezdev.repository;

import com.mrodriguezdev.exception.ExpenseIdNotFoundException;
import com.mrodriguezdev.model.Expense;
import com.mrodriguezdev.model.ExpenseWrapper;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ExpenseRepositoryImpl implements ExpenseRepository {
    ExpenseWrapperRepository expenseWrapperRepository = new ExpenseWrapperRepositoryImpl();

    @Override
    public Expense save(String description, Double amount) {
        Expense expense = new Expense(this.expenseWrapperRepository.generateNewId(), description, amount, LocalDateTime.now());
        ExpenseWrapper expenseWrapper = this.expenseWrapperRepository.loadExpenses();
        expenseWrapper.getExpenses().add(expense);
        this.expenseWrapperRepository.saveExpenses(expenseWrapper);
        this.expenseWrapperRepository.saveNewId(expense.getId());
        return expense;
    }

    @Override
    public void update(Expense expense) {
        ExpenseWrapper expenseWrapper = new ExpenseWrapper();
        expenseWrapper.setExpenses(listAll());
        List<Expense> expenses = expenseWrapper.getExpenses().stream()
                .filter(e -> e.getId().equals(expense.getId()))
                .map(e -> expense)
                .toList();
        expenseWrapper.setExpenses(expenses);
        this.expenseWrapperRepository.saveExpenses(expenseWrapper);
    }

    @Override
    public Expense findById(Long id) {
        ExpenseWrapper expenseWrapper = expenseWrapperRepository.loadExpenses();
        List<Expense> expenses = expenseWrapper.getExpenses();

        if (expenses.isEmpty()) {
            throw new ExpenseIdNotFoundException("No se encontraron gastos registrados.");
        }

        return expenses.stream()
                .filter(ex -> ex.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void delete(Expense expense) {
        ExpenseWrapper expenseWrapper = new ExpenseWrapper();
        expenseWrapper.setExpenses(listAll());
        expenseWrapper.getExpenses().remove(expense);
        this.expenseWrapperRepository.saveExpenses(expenseWrapper);
    }

    @Override
    public List<Expense> listAll() {
        ExpenseWrapper expenseWrapper = this.expenseWrapperRepository.loadExpenses();
        if (Objects.isNull(expenseWrapper)) return Collections.emptyList();
        return expenseWrapper.getExpenses();
    }

    @Override
    public void exportToCsv(List<Expense> expenses) {
        ExpenseWrapper expenseWrapper = new ExpenseWrapper();
        expenseWrapper.setExpenses(expenses);
        this.expenseWrapperRepository.exportToCsv(expenseWrapper);
    }
}
