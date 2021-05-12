package com.example.demo.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UsernameAndPasswordAuthenticationRequest {
    private String username;
    private String password;
}
