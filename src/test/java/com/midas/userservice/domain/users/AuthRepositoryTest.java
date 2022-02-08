package com.midas.userservice.domain.users;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AuthRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthRepository authRepository;

    @Test
    public void Auth정보찾기_By회원ID(){
        // given
        UserEntity user = userRepository.save(UserEntity.builder()
                .email("midas@gmail.com")
                .name("midas")
                .encryptedPwd("1234")
                .build());

        AuthEntity auth = authRepository.save(AuthEntity.builder()
                .refreshToken("refresh")
                .user(user)
                .build());

        // when
        AuthEntity findAuth = authRepository.findByUserId(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 Auth 정보는 존재하지 않습니다. userId=" + user.getId()));

        // then
        System.out.println("findAuth: " + findAuth.getId() + ", " + findAuth.getRefreshToken());
        assertThat(findAuth.getId()).isEqualTo(auth.getId());
        assertThat(findAuth.getRefreshToken()).isEqualTo(auth.getRefreshToken());
    }
}