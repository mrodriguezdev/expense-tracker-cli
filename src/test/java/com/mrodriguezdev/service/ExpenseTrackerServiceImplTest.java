package com.mrodriguezdev.service;

import com.mrodriguezdev.exception.IncompleteExpenseUpdateException;
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
        IncompleteExpenseUpdateException ieue = assertThrows(IncompleteExpenseUpdateException.class, () -> this.service.add(null, 15.00));
        assertTrue(ieue.getMessage().contains("Error al intentar guardar el gasto"));
        verify(repository, never()).save(null, 15.00);
    }


    @Test
    void update() {
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