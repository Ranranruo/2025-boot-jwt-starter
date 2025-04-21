package com.example.demo.Auth;

import lombok.Getter;

@Getter
public enum AuthRegExp {
    USERNAME("^[a-zA-Z0-9]{5,20}$"),
    PASSWORD("^(?=.*[0-9])(?=.*[!@#$%^&*()])[a-zA-Z0-9!@#$%^&*()]{8,16}$"),
    DISPLAY_NAME("^[a-zA-Z0-9가-힣]{2,16}$"),
    EMAIL("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");

    private final String pattern;

    // 생성자
    AuthRegExp(String pattern) {
        this.pattern = pattern;
    }
}
