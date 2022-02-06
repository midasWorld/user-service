package com.midas.userservice.web.dto.auth;

import com.midas.userservice.domain.auth.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RoleResponseDto {
    private String name;

    @Builder
    public RoleResponseDto(Role role) {
        this.name = role.getName();
    }
}
