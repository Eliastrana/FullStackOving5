package org.example.springbootbackend.security;


import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    @NonNull
    private String username;


    @NonNull
    private String password;
}