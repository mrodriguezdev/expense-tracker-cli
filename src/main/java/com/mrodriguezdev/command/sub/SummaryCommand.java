package com.mrodriguezdev.command.sub;

import com.mrodriguezdev.service.ExpenseTrackerFactory;
import com.mrodriguezdev.service.ExpenseTrackerService;
import picocli.CommandLine;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "summary",
        aliases = {"overview", "report"},
        version = "1.0.0",
        mixinStandardHelpOptions = true,
        requiredOptionMarker = '*',
        description = "Genera un resumen de tus gastos. Puedes especificar un mes para obtener el total de gastos de ese mes en particular. " +
                "Si no se especifica un mes, se mostrará el total general de todos los gastos registrados. " +
                "Este comando te ayuda a mantener un control preciso sobre tus finanzas y evaluar tu gasto en diferentes períodos.",
        header = "Sub-comando para Generar Resumen de Gastos",
        optionListHeading = "%nOpciones disponibles:%n",
        footerHeading = "%nInformación Adicional:%n",
        footer = "%nDesarrollado por mrodriguezdev - © 2024"
)
public class SummaryCommand implements Callable<Integer> {
    @CommandLine.Option(
            names = {"--month", "--m"},
            description = "Número del mes para el cual deseas obtener el resumen de gastos"
    )
    private Integer month;

    @CommandLine.Option(
            names = {"--export", "--e"},
            description = "Exporta el resumen de gastos a un archivo CSV en el directorio actual. Incluye todos los gastos registrados en el resumen y genera un archivo con un nombre basado en la fecha actual.",
            defaultValue = "false"
    )
    private Boolean export;

    private ExpenseTrackerService service;

    private SummaryCommand() {
        this.service = ExpenseTrackerFactory.getService();
    }

    @Override
    public Integer call() {
        double summary;
        if (Objects.isNull(this.month)) {
            summary = this.service.summary(this.export);
            System.out.printf("[summary] Total de gastos $%.2f%n", summary);
        } else {
            summary = this.service.summaryOf(this.export, this.month);
            String monthDesc = Month.of(this.month).getDisplayName(TextStyle.FULL, Locale.forLanguageTag("es"));
            System.out.printf("[summary] Total de gastos para %s $%.2f%n", monthDesc, summary);
        }
        if (this.export) System.out.println("[summary] Gastos exportados exitosamente.");
        return 0;
    }
}
