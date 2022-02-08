package com.midas.userservice.web.dto.users;

import com.midas.userservice.domain.users.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpRequestDto {
    private String email;
    private String name;
    private String password;

    @Builder
    public SignUpRequestDto(String email, String name, String password) {
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
