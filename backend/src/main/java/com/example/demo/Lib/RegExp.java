package com.example.demo.Lib;

import lombok.Getter;

@Getter
public enum RegExp  {
    private String username = "^[a-zA-Z0-9]{5,20}$";
    private String password = "^(?=.*[0-9])(?=.*[!@#$%^&*()])[a-zA-Z0-9!@#$%^&*()]{8,16}$";
    private String displayName = "^[a-zA-Z0-9가-힣]{2,16}$";
    private String email = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
}
