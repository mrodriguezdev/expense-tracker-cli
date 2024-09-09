package com.mrodriguezdev.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mrodriguezdev.exception.JsonUtilException;

import java.time.LocalDateTime;
import java.util.Objects;

public class JsonUtil {
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();

    public static String toJson(Object obj) {
        if (Objects.isNull(obj)) {
            throw new JsonUtilException("El objeto a serializar no puede ser nulo.");
        }
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        if (Objects.isNull(json) || Objects.isNull(clazz)) {
            throw new JsonUtilException("El JSON o la clase no pueden ser nulos.");
        }
        return gson.fromJson(json, clazz);
    }
}