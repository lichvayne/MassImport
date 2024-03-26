package com.example.massimportuniversalendpoint.mapper;

import com.example.massimportuniversalendpoint.annotations.EnumCellMapper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

@EnumCellMapper(type = CellType.STRING)
public class CellStringMapper implements CellValueMapper<String> {
    @Override
    public String getValue(Cell cell) {
        return cell.getStringCellValue();
    }
}
