package com.mrodriguezdev.command.sub;

import com.mrodriguezdev.service.ExpenseTrackerFactory;
import com.mrodriguezdev.service.ExpenseTrackerService;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "delete",
        aliases = {"remove", "discard"},
        version = "1.0.0",
        mixinStandardHelpOptions = true,
        requiredOptionMarker = '*',
        description = "Elimina un gasto de la lista en 'expense-tracker-cli'. " +
                "Especifica el identificador único del gasto que deseas eliminar. " +
                "Este comando es útil para eliminar registros de gastos que ya no son necesarios o son incorrectos, manteniendo tus datos actualizados.",
        header = "Sub-comando para Eliminar Gasto",
        optionListHeading = "%nOpciones disponibles:%n",
        footerHeading = "%nInformación Adicional:%n",
        footer = "%nDesarrollado por mrodriguezdev - © 2024"
)
public class DeleteCommand implements Callable<Integer> {
    @CommandLine.Option(names = {"--id", "--i"}, description = "Identificador único del gasto que deseas eliminar", required = true)
    private Long id;

    private ExpenseTrackerService service;

    private DeleteCommand() {
        this.service = ExpenseTrackerFactory.getService();
    }

    @Override
    public Integer call() {
        this.service.delete(id);
        System.out.printf("[delete] Gasto eliminado exitosamente (ID: %d)%n", id);
        return 0;
    }
}
