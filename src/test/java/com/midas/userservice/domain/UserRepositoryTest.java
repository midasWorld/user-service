package com.midas.userservice.domain;

import com.midas.userservice.domain.users.UserEntity;
import com.midas.userservice.domain.users.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Transactional
    @Test
    public void 사용자_저장하기(){
        // given
        String email = "midas@gmail.com";
        String name = "midas";
        String password = "1234";

        userRepository.save(UserEntity.builder()
                        .email(email)
                        .name(name)
                        .password(password)
                        .build());

        // when
        UserEntity findOne = userRepository.findByEmail(email);

        // then
        assertThat(findOne.getName()).isEqualTo(name);
        assertThat(findOne.getPassword()).isEqualTo(password);
    }
}