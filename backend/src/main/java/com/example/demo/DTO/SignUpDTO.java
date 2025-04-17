package com.example.demo.DTO;

import lombok.*;

@Setter
@Getter
public class SignUpDTO implements DTO{
    private final String username;
    private final String password;
    private final String displayName;
    private final String email;

    SignUpDTO(String username, String password, String displayName, String email) {
        this.username = username;
        this.password = password;
        this.displayName = displayName;
        this.email = email;
    }
    SignUpDTO(String username, String password, String displayName) {
        this.username = username;
        this.password = password;
        this.displayName = displayName;
        this.email = null;
    }

    @Override
    public boolean isValid() {
        if(this.username == null | this.password == null | this.displayName == null){
            return false;
        }
        return true;
    }

    public boolean isEmailEmpty() {
        return this.email == null || this.email.isEmpty();
    }
}
