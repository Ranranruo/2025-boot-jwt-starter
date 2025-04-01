package com.example.demo.DTO;

import lombok.*;

@Setter
@Getter
public class SignUpDTO implements DTO{
    private final String username;
    private final String password;
    private final String displayname;
    private final String email;

    SignUpDTO(String username, String password, String displayname, String email) {
        this.username = username;
        this.password = password;
        this.displayname = displayname;
        this.email = email;
    }
    SignUpDTO(String username, String password, String displayname) {
        this.username = username;
        this.password = password;
        this.displayname = displayname;
        this.email = null;
    }

    @Override
    public boolean isValid() {
        if(this.username == null | this.password == null | this.displayname == null){
            return false;
        }
        return true;
    }

    public boolean isEmailEmpty() {
        return this.email == null || this.email.isEmpty();
    }
}
