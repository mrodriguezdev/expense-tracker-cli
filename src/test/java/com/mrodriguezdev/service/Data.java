package com.mrodriguezdev.service;

import com.mrodriguezdev.model.Expense;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Data {
    public static final List<Expense> EXPENSES = Arrays.asList(
            new Expense(1L, "Supermercado", 45.30, LocalDateTime.of(2024, 1, 10, 10, 30), LocalDateTime.of(2024, 1, 10, 10, 30)),
            new Expense(2L, "Alquiler", 1200.00, LocalDateTime.of(2024, 1, 1, 9, 0), LocalDateTime.of(2024, 1, 1, 9, 0)),
            new Expense(3L, "Factura de electricidad", 75.60, LocalDateTime.of(2024, 1, 15, 16, 45), LocalDateTime.of(2024, 1, 15, 16, 45)),
            new Expense(4L, "Transporte", 20.00, LocalDateTime.of(2024, 2, 5, 8, 15), LocalDateTime.of(2024, 2, 5, 8, 15)),
            new Expense(5L, "Comida en restaurante", 35.75, LocalDateTime.of(2024, 2, 10, 13, 30), LocalDateTime.of(2024, 2, 10, 13, 30)),
            new Expense(6L, "Suscripción a internet", 50.00, LocalDateTime.of(2024, 2, 12, 18, 0), LocalDateTime.of(2024, 2, 12, 18, 0)),
            new Expense(7L, "Ropa", 120.50, LocalDateTime.of(2024, 3, 1, 11, 20), LocalDateTime.of(2024, 3, 1, 11, 20)),
            new Expense(8L, "Gimnasio", 40.00, LocalDateTime.of(2024, 3, 5, 7, 30), LocalDateTime.of(2024, 3, 5, 7, 30))
    );
    public static final Expense NEW_EXPENSE = new Expense(9L, "Libro", 15.00, LocalDateTime.of(2024, 3, 8, 14, 30));
    public static final Expense UPDATE_EXPENSE = new Expense(10L, "Cena en restaurante", 60.00, LocalDateTime.of(2024, 3, 10, 19, 0), LocalDateTime.of(2024, 3, 12, 19, 0));
    public static final Expense DELETE_EXPENSE = new Expense(9L, "Libro", 15.00, LocalDateTime.of(2024, 3, 8, 14, 30));
}
