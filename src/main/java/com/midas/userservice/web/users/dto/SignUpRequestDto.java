package com.midas.userservice.web.users.dto;

import com.midas.userservice.domain.users.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SignUpRequestDto {
    private String email;
    private String name;
    private String password;
    private List<RoleSaveRequestDto> roles;

    @Builder
    public SignUpRequestDto(String email, String name, String password, List<RoleSaveRequestDto> roles) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.roles = roles;
    }

    public UserEntity toEntity(String encryptedPwd) {
        return UserEntity.builder()
                .email(email)
                .name(name)
                .encryptedPwd(encryptedPwd)
                .build();
    }
}
