package com.example.massimportuniversalendpoint.provider;

import com.example.massimportuniversalendpoint.annotations.EnumExcelService;
import com.example.massimportuniversalendpoint.enums.ServiceType;
import com.example.massimportuniversalendpoint.service.ExcelService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Getter
public class ExcelServiceProvider {
    private final Map<ServiceType, ExcelService> excelServices;

    @Autowired
    public ExcelServiceProvider(ApplicationContext context) {
        excelServices = context
                .getBeansOfType(ExcelService.class)
                .entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry
                                .getValue()
                                .getClass()
                                .getAnnotation(EnumExcelService.class)
                                .type(),
                        Map.Entry::getValue,
                        (existing, additional) -> additional,
                        () -> new EnumMap<>(ServiceType.class)
                ));
    }

    public ExcelService getExcelService(ServiceType type) {
        return excelServices.get(type);
    }
}
