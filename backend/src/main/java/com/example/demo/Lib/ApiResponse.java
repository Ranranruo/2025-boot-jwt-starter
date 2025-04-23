package com.example.demo.Lib;

import com.example.demo.Common.Response.ResponseMessage;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiResponse<T> {
    private boolean success;
    private ResponseMessage message;
    private T data;
}