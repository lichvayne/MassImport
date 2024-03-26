package com.example.massimportuniversalendpoint.entity;

import com.example.massimportuniversalendpoint.annotations.ExcelColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @ExcelColumn(columnIndex = 0)
    private String profession;
    @ExcelColumn(columnIndex = 1)
    private String firstName;
    @ExcelColumn(columnIndex = 2)
    private String lastName;
    @ExcelColumn(columnIndex = 3)
    private String email;
}
