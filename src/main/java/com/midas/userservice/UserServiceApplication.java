package com.midas.userservice;

import com.midas.userservice.service.UserService;
import com.midas.userservice.web.dto.auth.RoleSaveRequestDto;
import com.midas.userservice.web.dto.auth.UserSaveRequestDto;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

			userService.saveUser(UserSaveRequestDto.builder().email("midas@gmail.com").name("midas").password("1234").build());
			userService.saveUser(UserSaveRequestDto.builder().email("john@gmail.com").name("john").password("1234").build());
			userService.saveUser(UserSaveRequestDto.builder().email("linda@gmail.com").name("linda").password("1234").build());
			userService.saveUser(UserSaveRequestDto.builder().email("smith@gmail.com").name("smith").password("1234").build());

			userService.addRoleToUser("john@gmail.com", "ROLE_USER");
			userService.addRoleToUser("john@gmail.com", "ROLE_MANAGER");
			userService.addRoleToUser("smith@gmail.com", "ROLE_MANAGER");
			userService.addRoleToUser("linda@gmail.com", "ROLE_ADMIN");
			userService.addRoleToUser("midas@gmail.com", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("midas@gmail.com", "ROLE_ADMIN");
			userService.addRoleToUser("midas@gmail.com", "ROLE_USER");
		};
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
