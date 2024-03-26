package com.example.massimportuniversalendpoint.entity;

import jakarta.persistence.*;

@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "Sequence",sequenceName = "SEQUENCE", initialValue = 1)
    protected Long id;
}
