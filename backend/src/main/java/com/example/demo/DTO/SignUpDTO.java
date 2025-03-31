package com.example.demo.DTO;

import lombok.*;

@Setter
@Getter
@RequiredArgsConstructor
public class SignUpDTO {
    private final String username;
    private final String password;
    private final String displayname;
}
