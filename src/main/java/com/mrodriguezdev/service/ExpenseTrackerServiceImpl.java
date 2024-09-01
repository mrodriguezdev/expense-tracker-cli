package com.mrodriguezdev.service;

import com.mrodriguezdev.model.Expense;

import java.util.List;

public class ExpenseTrackerServiceImpl implements ExpenseTrackerService {
    @Override
    public Expense add(String description, Double amount) {
        return null;
    }

    @Override
    public void update(Long id, String description, Double amount) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Expense> list() {
        return null;
    }

    @Override
    public Double summary() {
        return null;
    }

    @Override
    public Double summaryOf(int month) {
        return null;
    }
}
