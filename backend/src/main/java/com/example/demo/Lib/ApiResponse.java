package com.example.demo.Lib;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

//public class ApiResponse<T> {
//    private final boolean success;
//    private final HttpStatus status;
//    private final String message;
//    private final T data;
//
//    public ApiResponse(boolean success, String message) {
//        this(true, message, null);
//    }
//
//    public ApiResponse(boolean success, String message, T data) {
//        this.success = success;
//        this.message = message;
//        this.data = data;
//    }
//
//    public static ResponseEntity<ApiResponse> ok() {
//        return ResponseEntity.ok(new ApiResponse(true, H.SUCCESS.getMessage()));
//    }
//
//    public static <T> ResponseEntity<ApiResponse<T>> ok(T data) {
//        return ResponseEntity.ok(new ApiResponse<T>(true, ApiStatus.SUCCESS.getMessage(), data));
//    }
//
//    public static <T> ResponseEntity<ApiResponse<T>> ok(T data, String message) {
//        return ResponseEntity.ok(new ApiResponse<T>(true, message, data));
//    }
//
//    public static <T> ResponseEntity<ApiResponse<T>> error(ApiStatus status) {
//        ApiResponse<T> response = new ApiResponse<>(false, status.getMessage());
//        return ResponseEntity.status(status.getHttpStatus()).body(response);
//    }
//
//    // 데이터 포함 실패 응답
//    public static <T> ResponseEntity<ApiResponse<T>> error(ApiStatus status, T data) {
//        ApiResponse<T> response = new ApiResponse<T>(false, status.getMessage(), data);
//        return ResponseEntity.status(status.getHttpStatus()).body(response);
//    }
//}