package com.mrodriguezdev.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;
import java.util.Objects;

public class JsonUtil {
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();

    public static String toJson(Object obj) {
        if (Objects.isNull(obj)) {
            throw new IllegalArgumentException("El objeto a serializar no puede ser nulo.");
        }
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        if (Objects.isNull(json) || Objects.isNull(clazz)) {
            throw new IllegalArgumentException("El JSON o la clase no pueden ser nulos.");
        }
        return gson.fromJson(json, clazz);
    }
}