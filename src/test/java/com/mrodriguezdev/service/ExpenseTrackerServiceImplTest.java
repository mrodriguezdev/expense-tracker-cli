package com.mrodriguezdev.service;

import com.mrodriguezdev.exception.ExpenseIdNotFoundException;
import com.mrodriguezdev.exception.MissingExpenseFieldException;
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
        when(repository.findById(expenseId)).thenReturn(Data.UPDATED_EXPENSE);

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
        verify(repository, never()).update(Data.UPDATED_EXPENSE);
    }

    @Test
    void testUpdateExpenseThrowsWhenIdNotFound() {
        when(repository.findById(10L)).thenReturn(null);

        Expense expense = Data.UPDATED_EXPENSE;
        ExpenseIdNotFoundException e = assertThrows(ExpenseIdNotFoundException.class, () -> this.service.update(10L, expense.getDescription(), expense.getAmount()));
        assertTrue(e.getMessage().contains("No se encontró un gasto con ID:"));
        verify(repository, never()).update(Data.UPDATED_EXPENSE);
    }

    @Test
    void delete() {
    }

    @Test
    void testListExpenses() {
        when(repository.listAll()).thenReturn(Data.EXPENSES);

        List<Expense> expenses = this.service.list();

        assertNotNull(expenses);
        assertFalse(expenses.isEmpty());
        assertEquals(Data.EXPENSES.size(), expenses.size());
    }

    @Test
    void testListExpensesThrowsNoExpensesFound() {
        when(repository.listAll()).thenReturn(Collections.emptyList());

        NoExpensesFoundException e = assertThrows(NoExpensesFoundException.class, () -> this.service.list());
        assertTrue(e.getMessage().contains("No se encontraron gastos registrados en el sistema."));
    }

    @Test
    void summary() {
    }

    @Test
    void summaryOf() {
    }

    @Test
    void exportToCsv() {
    }
}