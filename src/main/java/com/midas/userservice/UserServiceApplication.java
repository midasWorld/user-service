package com.midas.userservice;

import com.midas.userservice.domain.users.UserEntity;
import com.midas.userservice.service.UserService;
import com.midas.userservice.web.dto.users.RoleSaveRequestDto;
import com.midas.userservice.web.dto.users.UserSaveRequestDto;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableEncryptableProperties
@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(RoleSaveRequestDto.builder().name("ROLE_USER").build());
			userService.saveRole(RoleSaveRequestDto.builder().name("ROLE_MANAGER").build());
			userService.saveRole(RoleSaveRequestDto.builder().name("ROLE_ADMIN").build());
			userService.saveRole(RoleSaveRequestDto.builder().name("ROLE_SUPER_ADMIN").build());

			userService.saveUser(UserSaveRequestDto.builder()
							.email("midas@gmail.com")
							.name("midas")
							.password("1234")
							.build());
		};
	}
}
