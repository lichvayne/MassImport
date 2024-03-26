package com.example.massimportuniversalendpoint.mapper;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class FieldMapper {
    public static List<String> classToFieldList(Class<?> aClass) {
        return Arrays.stream(aClass.getDeclaredFields())
                .map(Field::getName)
                .toList();
    }
}
