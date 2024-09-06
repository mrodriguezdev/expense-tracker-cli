package com.mrodriguezdev.util;

import com.mrodriguezdev.exception.FileUtilException;
import com.mrodriguezdev.model.Expense;

import java.io.*;
import java.util.List;

public class FileUtil {

    public static void create(String name, String content) {
        File file = new File(name);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            if (!parentDir.mkdirs()) {
                throw new FileUtilException("Error creando el directorio: " + parentDir.getPath());
            }
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            writer.print(content);
        } catch (IOException e) {
            throw new FileUtilException("Error creando el archivo: " + name, e);
        }
    }

    public static String readTxtAsString(String name) {
        File file = new File(name);
        if (!file.exists()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new FileUtilException("Error leyendo el archivo: " + name, e);
        }

        return sb.toString().trim();
    }

    public static String readJsonAsString(String name) {
        File file = new File(name);
        if (!file.exists() || file.length() == 0) {
            return "";
        }

        StringBuilder jsonContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line.trim());
            }
        } catch (IOException e) {
            throw new FileUtilException("Error leyendo el archivo: " + name, e);
        }

        return jsonContent.toString();
    }

    public static void createCsv(String name, List<Expense> content) {
        File file = new File(name);
        File parentDir = file.getParentFile();

        if (parentDir != null && !parentDir.exists()) {
            if (!parentDir.mkdirs()) {
                throw new FileUtilException("Error creando el directorio: " + parentDir.getPath());
            }
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            writer.println("ID,Description,Amount,Date,UpdatedDate");

            for (Expense expense : content) {
                String linea = String.format("\"%d\",\"%s\",\"%.2f\",\"%s\",\"%s\"",
                        expense.getId(),
                        expense.getDescription().replace("\"", "\"\""),
                        expense.getAmount(),
                        LocalDateTimeUtil.format(expense.getRegisteredAt()),
                        expense.getUpdatedAt() == null ? null : LocalDateTimeUtil.format(expense.getUpdatedAt())
                );
                writer.println(linea);
            }
        } catch (IOException e) {
            throw new FileUtilException("Error creando el archivo: " + name, e);
        }
    }
}
