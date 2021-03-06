package com.midas.userservice.web.users.dto;

import com.midas.userservice.domain.users.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RoleSaveRequestDto {
    private String name;

    @Builder
    public RoleSaveRequestDto(String name) {
        this.name = name;
    }

    public Role toEntity() {
        return Role.builder()
                .name(name)
                .build();
    }
}
