package com.example.demo.Lib;

import com.example.demo.Enum.ApiStatus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

public class ApiResponse<T> {
    private final boolean success;
    private final String message;
    private final T data;

    public ApiResponse(boolean success, String message) {
        this(true, message, null);
    }

    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static ResponseEntity<ApiResponse<Object>> ok() {
        return ResponseEntity.ok(new ApiResponse<Object>(true, "요청이 성공적으로 처리되었습니다."));
    }

    public static <T> ResponseEntity<ApiResponse<T>> ok(T data) {
        return ResponseEntity.ok(new ApiResponse<T>(true, "요청이 성공적으로 처리되었습니다.", data));
    }

    public static <T> ResponseEntity<ApiResponse<T>> ok(T data, String message) {
        return ResponseEntity.ok(new ApiResponse<T>(true, message, data));
    }

    public static <T> ResponseEntity<ApiResponse<T>> error(ApiStatus status) {
        ApiResponse<T> response = new ApiResponse<>(false, status.getMessage());
        return ResponseEntity.status(status.getHttpStatus()).body(response);
    }

    // 데이터 포함 실패 응답
    public static <T> ResponseEntity<ApiResponse<T>> error(ApiStatus status, T data) {
        ApiResponse<T> response = new ApiResponse<T>(false, status.getMessage(), data);
        return ResponseEntity.status(status.getHttpStatus()).body(response);
    }
}
