package com.midas.userservice.web.users.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignInReqestDto {
    private String email;
    private String password;

    @Builder
    public SignInReqestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
