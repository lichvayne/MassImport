package com.example.massimportuniversalendpoint.entity;

import com.example.massimportuniversalendpoint.annotations.ExcelColumn;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "task")
public class Task extends BaseEntity {
    @ExcelColumn(columnIndex = 0)
    @Column(name = "name", nullable = false)
    private String name;

    @ExcelColumn(columnIndex = 1)
    @Column(name = "description")
    private String description;

    @ExcelColumn(columnIndex = 2)
    @Column(name = "priority")
    private Integer priority;

    @ExcelColumn(columnIndex = 3)
    @Column(name = "start_date")
    private LocalDateTime startDate;

    @ExcelColumn(columnIndex = 4)
    @Column(name = "end_date")
    private LocalDateTime endDate;

    @ExcelColumn(columnIndex = 5)
    @Column(name = "deadline")
    private LocalDateTime deadline;

}
