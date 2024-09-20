package com.mrodriguezdev.service;

import com.mrodriguezdev.exception.ExpenseIdNotFoundException;
import com.mrodriguezdev.exception.MissingExpenseFieldException;
import com.mrodriguezdev.exception.MonthOutOfRangeException;
import com.mrodriguezdev.exception.NoExpensesFoundException;
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
        if (Objects.isNull(description) || Objects.isNull(amount)) {
            StringBuilder missingFields = new StringBuilder();
            if (Objects.isNull(description)) {
                missingFields.append("La descripción es obligatoria. ");
            }
            if (Objects.isNull(amount)) {
                missingFields.append("El monto es obligatorio. ");
            }
            missingFields.append("Por favor, completa estos campos antes de intentar nuevamente.");
            throw new MissingExpenseFieldException(missingFields.toString());
        }
        return this.expenseRepository.save(description, amount);
    }

    @Override
    public void update(Long id, String description, Double amount) {
        if (Objects.isNull(description) && Objects.isNull(amount)) {
            throw new MissingExpenseFieldException(String.format(
                    "Error al intentar actualizar el gasto con ID: %d. Debe proporcionar al menos una nueva descripción o un nuevo monto para realizar la actualización.",
                    id));
        }

        Expense expense = this.expenseRepository.findById(id);
        if (Objects.isNull(expense)) {
            throw new ExpenseIdNotFoundException(String.format(
                    "No se encontró un gasto con ID: %d. Verifique que el ID sea correcto y que el gasto exista.",
                    id));
        }

        if (Objects.nonNull(description)) expense.setDescription(description);
        if (Objects.nonNull(amount)) expense.setAmount(amount);
        expense.setUpdatedAt(LocalDateTime.now());
        this.expenseRepository.update(expense);
    }

    @Override
    public void delete(Long id) {
        Expense expense = this.expenseRepository.findById(id);
        if (Objects.isNull(expense))
            throw new ExpenseIdNotFoundException(String.format(
                    "No se encontró un gasto con ID: %d%n. Verifique que el ID sea correcto y que el gasto exista.",
                    id));
        this.expenseRepository.delete(expense);
    }

    @Override
    public List<Expense> list() {
        List<Expense> expenses = expenseRepository.listAll();
        if (expenses.isEmpty()) {
            throw new NoExpensesFoundException("No se encontraron gastos registrados en el sistema. Por favor, asegúrese de haber ingresado al menos un gasto.");
        }
        return expenses;
    }

    @Override
    public Double summary(boolean export) {
        List<Expense> expenses = list();
        LocalDateTime currentDate = LocalDateTime.now();
        int currentYear = currentDate.getYear();
        List<Expense> filteredExpenses = expenses.stream()
                .filter(expense -> currentYear == expense.getRegisteredAt().getYear())
                .toList();
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
            throw new MonthOutOfRangeException("El mes debe estar entre 1 y 12. Por favor, verifique el valor ingresado.");
        }
        List<Expense> expenses = list();
        LocalDateTime currentDate = LocalDateTime.now();
        int currentYear = currentDate.getYear();
        List<Expense> filteredExpenses = expenses.stream()
                .filter(expense -> currentYear == expense.getRegisteredAt().getYear() &&
                        month == expense.getRegisteredAt().getMonthValue()).toList();
        if (export) {
            this.expenseRepository.exportToCsv(filteredExpenses);
        }
        return filteredExpenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }
}
