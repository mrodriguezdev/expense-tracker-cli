package com.mrodriguezdev.repository;

import com.mrodriguezdev.model.ExpenseWrapper;
import com.mrodriguezdev.util.FileUtil;
import com.mrodriguezdev.util.JsonUtil;
import com.mrodriguezdev.util.LocalDateTimeUtil;

import java.io.File;
import java.time.LocalDateTime;

public class ExpenseWrapperRepositoryImpl implements ExpenseWrapperRepository {
    private static final String EXPENSES_FILE = System.getProperty("user.dir") + "/expenses.json";
    private static final String IDS_FILE = System.getProperty("user.dir") + "/ids.txt";
    private static final String EXPENSES_EXPORT_FILE = System.getProperty("user.dir") + File.separator +
            LocalDateTimeUtil.format(LocalDateTime.now(), LocalDateTimeUtil.FILE_PATTERN) + ".csv";

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

    @Override
    public void exportToCsv(ExpenseWrapper expenseWrapper) {
        FileUtil.createCsv(EXPENSES_EXPORT_FILE, expenseWrapper.getExpenses());
    }
}
