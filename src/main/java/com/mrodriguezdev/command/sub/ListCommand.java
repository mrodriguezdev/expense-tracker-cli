package com.mrodriguezdev.command.sub;

import com.mrodriguezdev.model.Expense;
import com.mrodriguezdev.service.ExpenseTrackerFactory;
import com.mrodriguezdev.service.ExpenseTrackerService;
import com.mrodriguezdev.util.LocalDateTimeUtil;
import picocli.CommandLine;

import java.util.List;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "list",
        aliases = {"show", "display"},
        version = "1.0.0",
        mixinStandardHelpOptions = true,
        description = "Muestra una lista de todos los gastos registrados en 'expense-tracker-cli'. " +
                "Puedes ver todos los gastos actuales con detalles como la descripción y el monto. " +
                "Este comando te ayuda a revisar todos tus registros de gastos para una mejor gestión financiera.",
        header = "Sub-comando para Listar Gastos",
        optionListHeading = "%nOpciones disponibles:%n",
        footerHeading = "%nInformación Adicional:%n",
        footer = "%nDesarrollado por mrodriguezdev - © 2024"
)
public class ListCommand implements Callable<Integer> {

    private ExpenseTrackerService service;

    private ListCommand() {
        this.service = ExpenseTrackerFactory.getService();
    }

    @Override
    public Integer call() {
        List<Expense> expenses = this.service.list();
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("# %-3s %-20s %-12s %-6s%n", "ID", "Date", "Description", "Amount"));
        for (Expense expense : expenses) {
            sb.append(String.format("# %-3d %-20s %-12s $%.2f%n",
                    expense.getId(),
                    LocalDateTimeUtil.format(expense.getRegisteredAt()),
                    expense.getDescription(),
                    expense.getAmount()));
        }
        System.out.print(sb);
        return 0;
    }
}
