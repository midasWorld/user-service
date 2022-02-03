package com.midas.userservice.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
    @Size(min = 2, message = "이름은 2글자 이상 입력해주세요.")
    private String name;

    @Size(min = 2, max = 20, message = "비밀번호는 2글자 이상, 20글자 이하로 입력해주세요.")
    private String password;

    @Builder
    public UserUpdateRequestDto(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
