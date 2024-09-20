package com.mrodriguezdev.service;

import com.mrodriguezdev.exception.MissingExpenseFieldException;
import com.mrodriguezdev.model.Expense;
import com.mrodriguezdev.repository.ExpenseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExpenseTrackerServiceImplTest {
    @Mock
    ExpenseRepository repository;
    @InjectMocks
    ExpenseTrackerServiceImpl service;

    @Test
    void add() {
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
    void addIncomplete() {
        MissingExpenseFieldException e = assertThrows(MissingExpenseFieldException.class, () -> this.service.add(null, 15.00));
        assertTrue(e.getMessage().contains("Por favor, completa estos campos antes de intentar nuevamente."));
        verify(repository, never()).save(null, 15.00);
    }

    @Test
    void update() {
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
    void delete() {
    }

    @Test
    void list() {
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