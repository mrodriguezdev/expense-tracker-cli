package com.mrodriguezdev;

import com.mrodriguezdev.command.ExpenseTrackerCliCommand;
import picocli.CommandLine;

public class App {
    public static void main(String[] args) {
        new CommandLine(new ExpenseTrackerCliCommand()).execute(args);
    }
}
