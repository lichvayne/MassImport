package com.example.massimportuniversalendpoint.service;

import org.apache.poi.ss.usermodel.Row;

import java.util.List;

public interface ExcelService<T> {
    void saveEntities(List<T> list);

    T createEntity(Row row);

     byte[] generateTemplate();
}
