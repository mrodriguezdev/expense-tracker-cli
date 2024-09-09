package com.mrodriguezdev;

import com.mrodriguezdev.command.ExpenseTrackerCliCommand;
import com.mrodriguezdev.exception.ExpenseTrackerExceptionHandler;
import picocli.CommandLine;

public class App {
    public static void main(String[] args) {
        CommandLine cmd = new CommandLine(new ExpenseTrackerCliCommand());
        cmd.setExecutionExceptionHandler(new ExpenseTrackerExceptionHandler());
        int exitCode = cmd.execute(args);
        System.exit(exitCode);
    }
}
