package com.example.massimportuniversalendpoint.mapper;

import com.example.massimportuniversalendpoint.annotations.EnumCellMapper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

@EnumCellMapper(type = CellType.BOOLEAN)
public class CellBooleanMapper implements CellValueMapper<Boolean> {
    @Override
    public Boolean getValue(Cell cell) {
        return cell.getBooleanCellValue();
    }
}
