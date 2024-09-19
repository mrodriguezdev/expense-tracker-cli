package com.mrodriguezdev.service;

import com.mrodriguezdev.exception.ExpenseNotFoundException;
import com.mrodriguezdev.exception.IncompleteExpenseUpdateException;
import com.mrodriguezdev.exception.InvalidMonthException;
import com.mrodriguezdev.model.Expense;
import com.mrodriguezdev.repository.ExpenseRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class ExpenseTrackerServiceImpl implements ExpenseTrackerService {
    private final ExpenseRepository expenseRepository;

    public ExpenseTrackerServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public Expense add(String description, Double amount) {
        if (Objects.isNull(description) || (Objects.isNull(amount))) {
            throw new IncompleteExpenseUpdateException("Error al intentar guardar el gasto. Debe proporcionar la descripción o un monto.");
        }
        return this.expenseRepository.save(description, amount);
    }

    @Override
    public void update(Long id, String description, Double amount) {
        if (Objects.isNull(description) && (Objects.isNull(amount))) {
            throw new IncompleteExpenseUpdateException(String.format(
                    "Error al intentar actualizar el gasto con ID: %d%n. Debe proporcionar al menos una nueva descripción o un nuevo monto.",
                    id));
        }
        Expense expense = this.expenseRepository.findById(id);
        if (Objects.isNull(expense))
            throw new ExpenseNotFoundException(String.format(
                    "No se encontró un gasto con ID: %d%n. Verifique que el ID sea correcto y que el gasto exista.",
                    id));
        if (Objects.nonNull(description)) expense.setDescription(description);
        if (Objects.nonNull(amount)) expense.setAmount(amount);
        expense.setUpdatedAt(LocalDateTime.now());
        this.expenseRepository.update(expense);
    }

    @Override
    public void delete(Long id) {
        Expense expense = this.expenseRepository.findById(id);
        if (Objects.isNull(expense))
            throw new ExpenseNotFoundException(String.format(
                    "No se encontró un gasto con ID: %d%n. Verifique que el ID sea correcto y que el gasto exista.",
                    id));
        this.expenseRepository.delete(expense);
    }

    @Override
    public List<Expense> list() {
        List<Expense> expenses = expenseRepository.listAll();
        if (expenses.isEmpty()) {
            throw new ExpenseNotFoundException("No se encontraron gastos registrados.");
        }
        return expenses;
    }

    @Override
    public Double summary(boolean export) {
        List<Expense> expenses = list();
        LocalDateTime currentDate = LocalDateTime.now();
        int currentYear = currentDate.getYear();
        List<Expense> filteredExpenses = expenses.stream()
                .filter(item -> currentYear == item.getRegisteredAt().getYear()).toList();
        if (export) {
            this.expenseRepository.exportToCsv(filteredExpenses);
        }
        return filteredExpenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    @Override
    public Double summaryOf(boolean export, int month) {
        if (month < 1 || month > 12) {
            throw new InvalidMonthException("El mes debe estar entre 1 y 12.");
        }
        List<Expense> expenses = list();
        LocalDateTime currentDate = LocalDateTime.now();
        int currentYear = currentDate.getYear();
        List<Expense> filteredExpenses = expenses.stream()
                .filter(item -> currentYear == item.getRegisteredAt().getYear() &&
                        month == item.getRegisteredAt().getMonthValue()).toList();
        if (export) {
            this.expenseRepository.exportToCsv(filteredExpenses);
        }
        return filteredExpenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }
}
