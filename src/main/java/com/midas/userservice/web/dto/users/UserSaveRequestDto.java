package com.midas.userservice.web.dto.users;

import com.midas.userservice.domain.users.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @Size(min = 2, message = "이름은 2글자 이상 입력해주세요.")
    private String name;

    @Size(min = 2, max = 20, message = "비밀번호는 2글자 이상, 20글자 이하로 입력해주세요.")
    private String password;

    @Builder
    public UserSaveRequestDto(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public UserEntity toEntity() {
        return UserEntity.builder()
                .email(email)
                .name(name)
                .password(password)
                .build();
    }
}
