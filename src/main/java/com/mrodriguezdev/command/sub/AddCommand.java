package com.mrodriguezdev.command.sub;

import com.mrodriguezdev.model.Expense;
import com.mrodriguezdev.service.ExpenseTrackerFactory;
import com.mrodriguezdev.service.ExpenseTrackerService;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "add",
        aliases = {"create", "plus"},
        version = "1.0.0",
        mixinStandardHelpOptions = true,
        requiredOptionMarker = '*',
        description = "Añade un nuevo gasto a tu lista de gastos en 'expense-tracker-cli'. " +
                "Especifica la descripción del gasto y el monto correspondiente. " +
                "Este comando permite gestionar de manera eficiente tus gastos y mantener un seguimiento preciso de tus finanzas.",
        header = "Sub-comando para Agregar Gasto",
        optionListHeading = "%nOpciones disponibles:%n",
        footerHeading = "%nInformación Adicional:%n",
        footer = "%nDesarrollado por mrodriguezdev - © 2024")
public class AddCommand implements Callable<Integer> {
    @CommandLine.Option(names = {"--description", "--d"}, description = "Descripción del gasto", required = true)
    private String description;

    @CommandLine.Option(names = {"--amount", "--a"}, description = "Monto del gasto en dólares", required = true)
    private double amount;

    private ExpenseTrackerService service;

    private AddCommand() {
        service = ExpenseTrackerFactory.getService();
    }

    @Override
    public Integer call() {
        Expense expense = this.service.add(description, amount);
        System.out.printf("[add] Gasto agregado exitosamente (ID: %d)%n", expense.getId());
        return 0;
    }
}
