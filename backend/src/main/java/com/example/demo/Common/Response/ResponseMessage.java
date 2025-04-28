package com.example.demo.Common.Response;

import lombok.Getter;

@Getter
public enum ResponseMessage {
    SUCCESS,
    CREATED,
    INVALID,
    NOT_FOUND,
    UNAUTHORIZED,
    EXISTS;
}
