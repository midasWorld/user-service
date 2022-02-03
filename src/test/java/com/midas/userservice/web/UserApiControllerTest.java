package com.midas.userservice.web;

import com.midas.userservice.domain.users.UserEntity;
import com.midas.userservice.domain.users.UserRepository;
import com.midas.userservice.exception.users.UserNotFoundException;
import com.midas.userservice.web.dto.users.UserSaveRequestDto;
import com.midas.userservice.web.dto.users.UserUpdateRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserApiControllerTest {

    @LocalServerPort private int port;

    @Autowired private TestRestTemplate restTemplate;
    @Autowired private UserRepository userRepository;

    @Test
    public void User_등록된다() throws Exception {
        // given
        String email = "midas@gmail.com";
        String name = "midas";
        String password = "1234";
        UserSaveRequestDto requestDto = UserSaveRequestDto.builder()
                .email(email)
                .name(name)
                .password(password)
                .build();

        String url = "http://localhost:" + port + "/api/v1/users";

        // when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        UserEntity findOne = userRepository.findByEmail(email);
        assertThat(findOne.getName()).isEqualTo(name);
        assertThat(findOne.getPassword()).isEqualTo(password);
    }

    @Test
    public void user_수정된다(){
        // given
        String email = "midas@gmail.com";
        String name = "midas";
        String password = "1234";
        UserEntity userEntity = userRepository.save(UserEntity.builder()
                        .email(email)
                        .name(name)
                        .password(password)
                        .build());

        Long updateId = userEntity.getId();

        String updateName = "updateMidas";
        String updatePassword = "12345";

        String url = "http://localhost:" + port + "/api/v1/users/" + updateId;

        UserUpdateRequestDto requestDto = UserUpdateRequestDto.builder()
                .name(updateName)
                .password(updatePassword)
                .build();

        // when
        restTemplate.put(url, requestDto);

        UserEntity findOne = userRepository.findById(updateId).get();

        // then
        assertThat(findOne.getName()).isEqualTo(updateName);
        assertThat(findOne.getPassword()).isEqualTo(updatePassword);
    }

    @Test
    public void user_삭제된다(){
        // given
        String email = "midas@gmail.com";
        String name = "midas";
        String password = "1234";
        UserEntity userEntity = userRepository.save(UserEntity.builder()
                .email(email)
                .name(name)
                .password(password)
                .build());

        Long deleteId = userEntity.getId();

        String url = "http://localhost:" + port + "/api/v1/users/" + deleteId;

        // when
        restTemplate.delete(url);

        // then
        assertThatThrownBy(() -> {
            userRepository.findById(deleteId).orElseThrow(() -> new UserNotFoundException("해당 사원은 존재하지 않습니다. id=" + deleteId));
        }).isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining("%d", deleteId);
    }
}