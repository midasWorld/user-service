package com.midas.userservice.web.dto.users;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenResponseDto {
    private String email;
    private String access_token;
    private String refresh_token;

    public TokenResponseDto(String email, String access_token, String refresh_token) {
        this.email = email;
        this.access_token = access_token;
        this.refresh_token = refresh_token;
    }
}
