package com.example.massimportuniversalendpoint;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
public class UserFields {

    private final List<String> fields = List.of("profession", "firstName", "lastName", "email", "dog", "car", "password");

}
