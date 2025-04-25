package com.example.demo.Common.Response;

import lombok.Getter;

@Getter
public enum ValidationStatus {
    SUCCESS,
    EMPTY,
    EXISTS,
    TOO_SHORT,
    TOO_LONG,
    INVALID,
}
