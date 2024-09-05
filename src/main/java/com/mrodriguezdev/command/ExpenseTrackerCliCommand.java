package com.mrodriguezdev.command;

import com.mrodriguezdev.command.sub.AddCommand;
import com.mrodriguezdev.command.sub.DeleteCommand;
import com.mrodriguezdev.command.sub.UpdateCommand;
import picocli.CommandLine;

@CommandLine.Command(
        name = "expense-tracker-cli",
        version = "1.0.0",
        mixinStandardHelpOptions = true,
        requiredOptionMarker = '*',
        description = "Expense Tracker CLI es una herramienta completa para gestionar tus gastos de manera eficiente. " +
                "Permite agregar, actualizar, eliminar y listar tus gastos, así como generar resúmenes para un seguimiento detallado de tus finanzas personales. " +
                "Mantén tus gastos organizados y controla tu presupuesto con facilidad.",
        header = "Expense Tracker CLI",
        optionListHeading = "%nOpciones disponibles:%n",
        footerHeading = "%nInformación Adicional:%n",
        subcommandsRepeatable = true,
        commandListHeading = "%nSubcomandos disponibles:%n",
        subcommands = {
                AddCommand.class,
                UpdateCommand.class,
                DeleteCommand.class
        }
)
public class ExpenseTrackerCliCommand implements Runnable {
    public static void main(String[] args) {
        int status = new CommandLine(new ExpenseTrackerCliCommand())
                .execute(args);
        System.exit(status);
    }

    @Override
    public void run() {
        System.out.println("[expense-tracker-cli] Bienvenido a Expense Tracker CLI.");
        System.out.println("Esta herramienta te permite gestionar y hacer un seguimiento de tus gastos de manera eficiente.");
        System.out.println("Utiliza los siguientes subcomandos para empezar:");
        System.out.println("  - add   : Añade un nuevo gasto.");
        System.out.println("  - update: Actualiza un gasto existente.");
        System.out.println("  - delete: Elimina un gasto.");
        System.out.println("  - list  : Lista todos los gastos.");
        System.out.println("  - summary: Muestra un resumen de tus gastos.");
        System.out.println("Para obtener más información sobre cada comando, utiliza la opción '-h' o '--help'.");
    }
}
