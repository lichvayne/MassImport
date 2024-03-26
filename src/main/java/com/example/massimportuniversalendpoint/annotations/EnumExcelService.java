package com.example.massimportuniversalendpoint.annotations;

import com.example.massimportuniversalendpoint.enums.ServiceType;
import org.springframework.stereotype.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Service
public @interface EnumExcelService {
    ServiceType type();
}
