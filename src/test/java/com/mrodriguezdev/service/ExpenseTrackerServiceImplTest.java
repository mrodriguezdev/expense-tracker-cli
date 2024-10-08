package com.mrodriguezdev.service;

import com.mrodriguezdev.exception.ExpenseIdNotFoundException;
import com.mrodriguezdev.exception.MissingExpenseFieldException;
import com.mrodriguezdev.exception.MonthOutOfRangeException;
import com.mrodriguezdev.exception.NoExpensesFoundException;
import com.mrodriguezdev.model.Expense;
import com.mrodriguezdev.repository.ExpenseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExpenseTrackerServiceImplTest {
    @Mock
    ExpenseRepository repository;
    @InjectMocks
    ExpenseTrackerServiceImpl service;

    @Test
    void testAddExpense() {
        when(repository.save("Libro", 15.00)).thenReturn(Data.NEW_EXPENSE);

        Expense expense = service.add("Libro", 15.00);

        assertNotNull(expense);
        assertNotNull(expense.getId());
        assertEquals(9L, expense.getId());
        assertEquals("Libro", expense.getDescription());
        assertEquals(15.00, expense.getAmount());
        assertNotNull(expense.getRegisteredAt());
        assertNull(expense.getUpdatedAt());
        verify(repository).save("Libro", 15.00);
    }

    @Test
    void testAddExpenseThrowsWhenMissingDescription() {
        MissingExpenseFieldException e = assertThrows(MissingExpenseFieldException.class, () -> this.service.add(null, 15.00));
        assertTrue(e.getMessage().contains("Por favor, completa estos campos antes de intentar nuevamente."));
        verify(repository, never()).save(null, 15.00);
    }

    @Test
    void testUpdateExpense() {
        long expenseId = 10L;
        when(repository.findById(expenseId)).thenReturn(Data.UPDATE_EXPENSE);

        service.update(expenseId, "Cena en restaurante", 60.0);

        verify(repository).update(argThat(expense ->
                expense.getDescription().equals("Cena en restaurante") &&
                        expense.getAmount().compareTo(60.0) == 0
        ));

        verify(repository).findById(expenseId);
    }

    @Test
    void testUpdateExpenseThrowsWhenIncomplete() {
        MissingExpenseFieldException e = assertThrows(MissingExpenseFieldException.class, () -> this.service.update(10L, null, null));
        assertTrue(e.getMessage().contains("Debe proporcionar al menos una nueva descripción o un nuevo monto para realizar la actualización."));
        verify(repository, never()).findById(anyLong());
        verify(repository, never()).update(Data.UPDATE_EXPENSE);
    }

    @Test
    void testUpdateExpenseThrowsWhenIdNotFound() {
        when(repository.findById(10L)).thenReturn(null);

        Expense expense = Data.UPDATE_EXPENSE;
        ExpenseIdNotFoundException e = assertThrows(ExpenseIdNotFoundException.class, () -> this.service.update(10L, expense.getDescription(), expense.getAmount()));
        assertTrue(e.getMessage().contains("No se encontró un gasto con ID:"));
        verify(repository, never()).update(Data.UPDATE_EXPENSE);
    }

    @Test
    void testDeleteExpense() {
        when(repository.findById(9L)).thenReturn(Data.DELETE_EXPENSE);
        doNothing().when(repository).delete(Data.DELETE_EXPENSE);

        this.service.delete(9L);

        verify(repository).findById(9L);
        verify(repository).delete(Data.DELETE_EXPENSE);
    }

    @Test
    void testDeleteExpenseThrowsWhenIdNotFound() {
        when(repository.findById(9L)).thenReturn(null);

        ExpenseIdNotFoundException e = assertThrows(ExpenseIdNotFoundException.class, () -> this.service.delete(9L));

        assertTrue(e.getMessage().contains("No se encontró un gasto con ID:"));
        verify(repository, never()).delete(Data.DELETE_EXPENSE);
    }

    @Test
    void testListExpenses() {
        when(repository.listAll()).thenReturn(Data.EXPENSES);

        List<Expense> expenses = this.service.list();

        assertNotNull(expenses);
        assertFalse(expenses.isEmpty());
        assertEquals(Data.EXPENSES.size(), expenses.size());

        verify(repository).listAll();
    }

    @Test
    void testListExpensesThrowsWhenNoExpensesFound() {
        when(repository.listAll()).thenReturn(Collections.emptyList());

        NoExpensesFoundException e = assertThrows(NoExpensesFoundException.class, () -> this.service.list());
        assertTrue(e.getMessage().contains("No se encontraron gastos registrados en el sistema."));
    }

    @Test
    void testSummary() {
        when(repository.listAll()).thenReturn(Data.EXPENSES);

        double summary = this.service.summary(false);
        double expectedSummary = Data.EXPENSES.stream()
                .mapToDouble(Expense::getAmount)
                .sum();

        assertNotEquals(0, summary);
        assertEquals(expectedSummary, summary, 0.001);

        verify(repository).listAll();
    }

    @Test
    void testSummaryOf() {
        when(repository.listAll()).thenReturn(Data.EXPENSES);

        double summary = this.service.summaryOf(false, 2);
        double expectedSummary = Data.EXPENSES.stream()
                .filter(expense -> expense.getRegisteredAt().getMonthValue() == 2)
                .mapToDouble(Expense::getAmount)
                .sum();

        assertNotEquals(0, summary);
        assertEquals(expectedSummary, summary, 0.001);

        verify(repository).listAll();
    }

    @Test
    void testSummaryOfThrowsWhenMonthOutOfRange() {
        MonthOutOfRangeException e = assertThrows(MonthOutOfRangeException.class, () -> this.service.summaryOf(false, 13));

        assertTrue(e.getMessage().contains("El mes debe estar entre 1 y 12."));

        verify(repository, never()).listAll();
    }
}