package com.example.massimportuniversalendpoint.mapper;

import com.example.massimportuniversalendpoint.annotations.EnumCellMapper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

@EnumCellMapper(type = CellType.NUMERIC)
public class CellNumericMapper implements CellValueMapper<Number> {
    @Override
    public Number getValue(Cell cell) {
        return cell.getNumericCellValue();
    }
}
