package com.example.massimportuniversalendpoint.enums;

import com.example.massimportuniversalendpoint.entity.BaseEntity;
import com.example.massimportuniversalendpoint.entity.Task;
import com.example.massimportuniversalendpoint.entity.User;
import lombok.Getter;

@Getter
public enum ServiceType {
    USER(User.class),
    TASK(Task.class);

    private final String entityName;
    private final Class<? extends BaseEntity> entityClass;

    ServiceType(Class<? extends BaseEntity> entityClass) {
        this.entityName = entityClass.getName();
        this.entityClass = entityClass;
    }
}
