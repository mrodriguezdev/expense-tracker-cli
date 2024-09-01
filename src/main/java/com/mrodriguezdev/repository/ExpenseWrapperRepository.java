package com.mrodriguezdev.repository;

import com.mrodriguezdev.model.ExpenseWrapper;
import com.mrodriguezdev.util.FileUtil;
import com.mrodriguezdev.util.JsonUtil;

public class ExpenseWrapperRepository implements Repository<ExpenseWrapper> {
    private static final String EXPENSES_FILE = System.getProperty("user.dir") + "/expenses.json";
    private static final String IDS_FILE = System.getProperty("user.dir") + "/ids.txt";

    @Override
    public Long generateNewId() {
        String content = FileUtil.readTxtAsString(IDS_FILE);
        return content.isEmpty() ? 1 : Long.parseLong(content.trim()) + 1;
    }

    @Override
    public ExpenseWrapper loadExpenses() {
        String content = FileUtil.readJsonAsString(EXPENSES_FILE);
        return content.isEmpty() ? new ExpenseWrapper() : JsonUtil.fromJson(content, ExpenseWrapper.class);
    }

    @Override
    public void saveExpenses(ExpenseWrapper expenseWrapper) {
        String json = JsonUtil.toJson(expenseWrapper);
        FileUtil.create(EXPENSES_FILE, json);
    }

    @Override
    public void saveNewId(Long newId) {
        FileUtil.create(IDS_FILE, String.valueOf(newId));
    }
}
