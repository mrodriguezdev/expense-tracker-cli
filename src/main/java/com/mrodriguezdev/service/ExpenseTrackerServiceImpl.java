package com.mrodriguezdev.service;

import com.mrodriguezdev.model.Expense;
import com.mrodriguezdev.model.ExpenseWrapper;
import com.mrodriguezdev.repository.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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
        if (Objects.isNull(description) && (Objects.isNull(amount))) {
            throw new RuntimeException(String.format(
                    "Error al intentar actualizar el gasto con ID: %d%n. Debe proporcionar al menos una nueva descripción o un nuevo monto.",
                    id));
        }

        ExpenseWrapper expenseWrapper = repository.loadExpenses();
        List<Expense> expenses = expenseWrapper.getExpenses();

        if (expenses == null || expenses.isEmpty()) {
            throw new RuntimeException("No se encontraron gastos registrados para actualizar.");
        }

        Expense expense = expenses.stream()
                .filter(ex -> ex.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format(
                        "No se encontró un gasto con ID: %d%n. Verifique que el ID sea correcto y que el gasto exista.",
                        id)));

        if (Objects.nonNull(description)) expense.setDescription(description);
        if (Objects.nonNull(amount)) expense.setAmount(amount);
        expense.setUpdatedAt(LocalDateTime.now());

        repository.saveExpenses(expenseWrapper);
    }

    @Override
    public void delete(Long id) {
        ExpenseWrapper expenseWrapper = repository.loadExpenses();
        List<Expense> expenses = expenseWrapper.getExpenses();

        if (expenses == null || expenses.isEmpty()) {
            throw new RuntimeException("No se encontraron gastos registrados para eliminar.");
        }

        Expense expense = expenses.stream()
                .filter(ex -> ex.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format(
                        "No se encontró un gasto con ID: %d%n. Verifique que el ID sea correcto y que el gasto exista.",
                        id)));

        expenses.remove(expense);
        repository.saveExpenses(expenseWrapper);
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
