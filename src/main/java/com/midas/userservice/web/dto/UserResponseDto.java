package com.midas.userservice.web.dto;

import com.midas.userservice.domain.users.UserEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {
    private Long id;
    private String email;
    private String name;
    private LocalDateTime modifiedDate;

    public UserResponseDto(UserEntity entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.name = entity.getName();
        this.modifiedDate = entity.getModifiedDate();
    }
}
