package com.midas.userservice.web.dto.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RoleToUserDto {
    private String email;
    private String roleName;

    @Builder
    public RoleToUserDto(String email, String roleName) {
        this.email = email;
        this.roleName = roleName;
    }
}
