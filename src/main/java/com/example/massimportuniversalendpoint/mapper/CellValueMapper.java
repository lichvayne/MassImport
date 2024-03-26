package com.example.massimportuniversalendpoint.mapper;

import org.apache.poi.ss.usermodel.Cell;

public interface CellValueMapper<T> {

    T getValue(Cell cell);
}
