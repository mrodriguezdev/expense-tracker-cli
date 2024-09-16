package com.mrodriguezdev.service;

import com.mrodriguezdev.model.Expense;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseTrackerServiceTest {
    ExpenseTrackerService service;

    @BeforeEach
    void setUp(TestInfo testInfo, TestReporter testReporter) {
        this.service = ExpenseTrackerFactory.getService();

        StringBuilder mensaje = new StringBuilder();
        mensaje.append("Ejecución de la prueba: ")
                .append(testInfo.getDisplayName())
                .append(" (Método: ");

        testInfo.getTestMethod().ifPresentOrElse(
                method -> mensaje.append(method.getName()).append(")"),
                () -> mensaje.append("No disponible)")
        );

        if (!testInfo.getTags().isEmpty()) {
            mensaje.append(" con las siguientes etiquetas: ")
                    .append(String.join(", ", testInfo.getTags()));
        } else {
            mensaje.append(" sin etiquetas.");
        }

        testReporter.publishEntry(mensaje.toString());
    }



    @Tag("add")
    @Nested
    @DisplayName("Pruebas del Comando para Agregar Gasto")
    class AddCommandTest {
        @Test
        @DisplayName("Debería agregar un gasto con monto decimal correctamente")
        void testAgregarGastoConMontoDecimal() {
            Expense expense = service.add("test", 12.5);
            assertNotNull(expense);
            assertEquals("test", expense.getDescription());
            assertEquals(12.5, expense.getAmount());
        }
    }
}