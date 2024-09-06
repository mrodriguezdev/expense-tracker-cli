package com.mrodriguezdev.service;

import com.mrodriguezdev.repository.ExpenseWrapperRepository;

public class ExpenseTrackerFactory {
    public static ExpenseTrackerService getService() {
        return new ExpenseTrackerServiceImpl(new ExpenseWrapperRepository());
    }
}
