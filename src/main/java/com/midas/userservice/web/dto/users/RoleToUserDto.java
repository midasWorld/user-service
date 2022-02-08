package com.midas.userservice.web.dto.users;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RoleToUserDto {
    private String email;
    private List<RoleSaveRequestDto> roles;

    @Builder
    public RoleToUserDto(String email, List<RoleSaveRequestDto> roles) {
        this.email = email;
        this.roles = roles;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("email: " + email + ", roles: ");
        for (RoleSaveRequestDto role : roles) {
            result.append(role.getName() + " ");
        }
        return result.toString();
    }
}
