package com.mrodriguezdev.command;

import com.github.lalyos.jfiglet.FigletFont;
import com.mrodriguezdev.command.sub.*;
import org.fusesource.jansi.AnsiConsole;
import picocli.CommandLine;

import java.io.IOException;

import static org.fusesource.jansi.Ansi.ansi;

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
                DeleteCommand.class,
                SummaryCommand.class,
                ListCommand.class
        }
)
public class ExpenseTrackerCliCommand implements Runnable {
    private static final String GREEN_TEXT = ansi().fgGreen().toString();
    private static final String RESET_TEXT = ansi().reset().toString();

    public static void main(String[] args) {
        int status = new CommandLine(new ExpenseTrackerCliCommand())
                .execute(args);
        System.exit(status);
    }

    @Override
    public void run() {
        AnsiConsole.systemInstall();
        try {
            String banner = FigletFont.convertOneLine("Expense Tracker CLI");
            System.out.println(GREEN_TEXT + banner + RESET_TEXT);
        } catch (IOException e) {
            System.err.println("Error al generar el banner. Por favor, revisa tu configuración.");
            e.printStackTrace();
            return;
        } finally {
            AnsiConsole.systemUninstall();
        }

        StringBuilder message = new StringBuilder();
        message.append("\n[expense-tracker-cli] Bienvenido a Expense Tracker CLI.\n\n")
                .append("Esta herramienta te permite gestionar y hacer un seguimiento de tus gastos de manera eficiente.\n\n")
                .append(GREEN_TEXT).append("Utiliza los siguientes subcomandos para empezar:\n\n").append(RESET_TEXT)
                .append("  - ").append(GREEN_TEXT).append("add").append(RESET_TEXT).append("    : Añade un nuevo gasto.\n")
                .append("  - ").append(GREEN_TEXT).append("update").append(RESET_TEXT).append(" : Actualiza un gasto existente.\n")
                .append("  - ").append(GREEN_TEXT).append("delete").append(RESET_TEXT).append(" : Elimina un gasto.\n")
                .append("  - ").append(GREEN_TEXT).append("list").append(RESET_TEXT).append("   : Lista todos los gastos.\n")
                .append("  - ").append(GREEN_TEXT).append("summary").append(RESET_TEXT).append(": Muestra un resumen de tus gastos.\n\n")
                .append("Para obtener más información sobre cada comando, utiliza la opción '-h' o '--help'.\n");

        System.out.println(message);
    }
}
