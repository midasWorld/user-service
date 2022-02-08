package com.midas.userservice.domain.users;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class RoleRepositoryTest {

    @Autowired
    RoleRepository roleRepository;

    @Test
    public void Role_FindByName_테스트(){
        // given
        Role savedRole = roleRepository.save(Role.builder()
                .name("ADMIN")
                .build());

        // when
        Role findRole = roleRepository.findByName("ADMIN").orElseThrow(() -> new IllegalArgumentException("해당 Role은 존재하지 않습니다."));

        // then
        System.out.println("role-name: " + findRole.getName());
        Assertions.assertThat(savedRole.getId()).isEqualTo(findRole.getId());
    }
}