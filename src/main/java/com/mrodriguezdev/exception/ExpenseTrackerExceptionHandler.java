package com.mrodriguezdev.exception;

import picocli.CommandLine;

public class ExpenseTrackerExceptionHandler implements CommandLine.IExecutionExceptionHandler {
    public int handleExecutionException(Exception ex, CommandLine cmd, CommandLine.ParseResult parseResult) {
        if (ex instanceof MonthOutOfRangeException) {
            System.err.println("[ERROR] " + ex.getMessage());
        } else if (ex instanceof ExpenseIdNotFoundException) {
            System.err.println("[ERROR] " + ex.getMessage());
        } else if (ex instanceof FileUtilException) {
            System.err.println("[ERROR] Error de archivo: " + ex.getMessage());
        } else if (ex instanceof JsonUtilException) {
            System.err.println("[ERROR] Error al procesar JSON: " + ex.getMessage());
        } else if (ex instanceof MissingExpenseFieldException) {
            System.err.println("[ERROR] " + ex.getMessage());
        } else {
            System.err.println("[ERROR] Ocurrió un error inesperado durante la ejecución del comando: " + ex.getMessage());
        }

        return cmd.getCommandSpec().exitCodeOnExecutionException();
    }
}