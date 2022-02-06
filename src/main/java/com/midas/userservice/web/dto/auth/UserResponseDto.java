package com.midas.userservice.web.dto.auth;

import com.midas.userservice.domain.auth.Role;
import com.midas.userservice.domain.auth.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private String email;
    private String name;
    private String password;
    private Collection<Role> roles;

    @Builder
    public UserResponseDto(UserEntity user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.password = user.getEncryptedPwd();
        this.roles = user.getRoles();
    }
}
