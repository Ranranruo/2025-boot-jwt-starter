package com.example.demo.Lib;

import lombok.Getter;

@Getter
public enum ValidationStatus {
    SUCCESS("SUCCESS"),
    EMPTY("EMPTY"),
    TOO_SHORT("TOO_SHORT"),
    TOO_LONG("TOO_LONG"),
    INVALID("INVALID");

    private String message;

    public String getMessage() {
        return this.message;
    }

    ValidationStatus (String message) {
        this.message = message;
    }
}
