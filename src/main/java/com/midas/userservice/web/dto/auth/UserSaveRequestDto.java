package com.midas.userservice.web.dto.auth;

import com.midas.userservice.domain.auth.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {
    private String email;
    private String name;
    private String password;

    @Builder
    public UserSaveRequestDto(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public UserEntity toEntity(String encryptedPwd) {
        return UserEntity.builder()
                .email(email)
                .name(name)
                .encryptedPwd(encryptedPwd)
                .build();
    }
}
