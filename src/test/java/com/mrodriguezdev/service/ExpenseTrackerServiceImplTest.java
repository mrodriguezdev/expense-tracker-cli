package com.mrodriguezdev.service;

import com.mrodriguezdev.model.Expense;
import com.mrodriguezdev.model.ExpenseWrapper;
import com.mrodriguezdev.repository.Repository;
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
    Repository<ExpenseWrapper> repository;
    @InjectMocks
    ExpenseTrackerServiceImpl service;

    @Test
    void add() {
        when(repository.generateNewId()).thenReturn(9L);
        when(repository.loadExpenses()).thenReturn(Data.EXPENSE_WRAPPER);
        doNothing().when(repository).saveExpenses(Data.EXPENSE_WRAPPER);
        doNothing().when(repository).saveNewId(9L);

        Expense expense = service.add("Libro", 15.00);

        assertNotNull(expense);
        assertEquals(9L, expense.getId());
        assertEquals("Libro", expense.getDescription());
        assertEquals(15.00, expense.getAmount());
        assertNotNull(expense.getRegisteredAt());
        assertNull(expense.getUpdatedAt());
        verify(repository).generateNewId();
        verify(repository).loadExpenses();
        verify(repository).saveExpenses(Data.EXPENSE_WRAPPER);
        verify(repository).saveNewId(9L);
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