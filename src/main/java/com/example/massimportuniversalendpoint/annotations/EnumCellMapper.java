package com.example.massimportuniversalendpoint.annotations;

import org.apache.poi.ss.usermodel.CellType;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface EnumCellMapper {
    CellType type();
}
