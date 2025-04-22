package com.example.demo.Lib;

import lombok.*;
import org.apache.el.util.Validation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
}