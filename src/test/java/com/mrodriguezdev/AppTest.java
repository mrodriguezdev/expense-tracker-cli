package com.mrodriguezdev;

import com.mrodriguezdev.service.ExpenseTrackerFactory;
import com.mrodriguezdev.service.ExpenseTrackerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    ExpenseTrackerService service;

    @BeforeEach
    void initTest() {
        this.service = ExpenseTrackerFactory.getService();
    }

    @Nested
    @DisplayName("Pruebas del Comando para Agregar Gasto")
    class AddCommandTest {

    }

    @Nested
    @DisplayName("Pruebas del Comando para Actualizar Gasto")
    class UpdateCommandTest {

    }

    @Nested
    @DisplayName("Pruebas del Comando para Eliminar Gasto")
    class DeleteCommandTest {

    }

    @Nested
    @DisplayName("Pruebas del Comando de Resumen")
    class SummaryCommandTest {

    }

    @Nested
    @DisplayName("Pruebas del Comando para Listar Gastos")
    class ListCommandTest {

    }
}