package com.example.massimportuniversalendpoint.provider;

import com.example.massimportuniversalendpoint.annotations.EnumCellMapper;
import com.example.massimportuniversalendpoint.mapper.CellValueMapper;
import lombok.Getter;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Component
public class CellMapperProvider {
    private final Map<CellType, CellValueMapper> cellMappers;

    @Autowired
    public CellMapperProvider(ApplicationContext context) {
        cellMappers = context
                .getBeansOfType(CellValueMapper.class)
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getValue().getClass().getAnnotation(EnumCellMapper.class).type(),
                        Map.Entry::getValue,
                        (existing, additional) -> additional,
                        () -> new EnumMap<>(CellType.class)
                ));
    }

    public CellValueMapper getMapperByCellType(CellType cellType) {
        return cellMappers
                .get(cellType);
    }
}
