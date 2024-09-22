package com.mrodriguezdev.service;

import com.mrodriguezdev.repository.ExpenseRepositoryImpl;

public class ExpenseTrackerFactory {
    public static ExpenseTrackerService getService() {
        return new ExpenseTrackerServiceImpl(new ExpenseRepositoryImpl());
    }
}
