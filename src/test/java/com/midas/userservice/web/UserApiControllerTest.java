package com.midas.userservice.web;

import com.midas.userservice.domain.users.UserEntity;
import com.midas.userservice.domain.users.UserRepository;
import com.midas.userservice.web.dto.UserSaveRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

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

        // then료
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        UserEntity findOne = userRepository.findByEmail(email);
        assertThat(findOne.getName()).isEqualTo(name);
        assertThat(findOne.getPassword()).isEqualTo(password);
    }
}