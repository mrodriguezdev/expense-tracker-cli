package com.mrodriguezdev.command.sub;

import com.mrodriguezdev.service.ExpenseTrackerFactory;
import com.mrodriguezdev.service.ExpenseTrackerService;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "update",
        aliases = {"change", "alter"},
        version = "1.0.0",
        mixinStandardHelpOptions = true,
        requiredOptionMarker = '*',
        description = "Actualiza un gasto existente en la lista de 'expense-tracker-cli'. " +
                "Proporciona el identificador del gasto que deseas actualizar, junto con la nueva descripción o el monto, o ambos. " +
                "Este comando permite modificar la información de un gasto previamente registrado para mantener tus registros financieros actualizados.",
        header = "Sub-comando para Actualizar Gasto",
        optionListHeading = "%nOpciones disponibles:%n",
        footerHeading = "%nInformación Adicional:%n",
        footer = "%nDesarrollado por mrodriguezdev - © 2024")
public class UpdateCommand implements Callable<Integer> {
    @CommandLine.Option(names = {"--id", "--i"}, description = "Identificador del gasto a actualizar", required = true)
    private Long id;

    @CommandLine.Option(names = {"--description", "--d"}, description = "Nueva descripción del gasto")
    private String description;

    @CommandLine.Option(names = {"--amount", "--a"}, description = "Nuevo monto del gasto en dólares")
    private Double amount;

    private ExpenseTrackerService service;

    private UpdateCommand() {
        this.service = ExpenseTrackerFactory.getService();
    }

    @Override
    public Integer call() {
        this.service.update(id, description, amount);
        System.out.printf("[add] Gasto actualizado exitosamente (ID: %d)%n", id);
        return 0;
    }
}
