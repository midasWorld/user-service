package com.midas.userservice.web.users.dto;

import com.midas.userservice.domain.users.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private String email;
    private String name;
    private String encryptedPwd;
    private List<String> roles;
    private LocalDateTime modifiedDate;

    public UserResponseDto(UserEntity user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.encryptedPwd = user.getEncryptedPwd();
        this.roles = user.getRoles().stream()
                .map(role -> role.getName())
                .collect(Collectors.toList());
        this.modifiedDate = user.getModifiedDate();
    }
}
