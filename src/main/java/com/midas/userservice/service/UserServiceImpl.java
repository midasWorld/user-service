package com.midas.userservice.service;

import com.midas.userservice.domain.auth.Role;
import com.midas.userservice.domain.auth.RoleRepository;
import com.midas.userservice.domain.auth.UserEntity;
import com.midas.userservice.domain.auth.UserRepository;
import com.midas.userservice.exception.auth.RoleNotFoundException;
import com.midas.userservice.exception.auth.UserNotFoundException;
import com.midas.userservice.web.dto.auth.RoleResponseDto;
import com.midas.userservice.web.dto.auth.RoleSaveRequestDto;
import com.midas.userservice.web.dto.auth.UserResponseDto;
import com.midas.userservice.web.dto.auth.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UserNotFoundException("해당 사원을 찾을 수 없습니다. email=" + username));
        log.info("{} User login", username);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getEncryptedPwd(), authorities);
    }

    @Override
    public UserResponseDto saveUser(UserSaveRequestDto requestDto) {
        log.info("Saving new user {} to the database", requestDto.getEmail());
        String encryptedPwd = passwordEncoder.encode(requestDto.getPassword());
        UserEntity savedUser = userRepository.save(requestDto.toEntity(encryptedPwd));
        return new UserResponseDto(savedUser);
    }

    @Override
    public RoleResponseDto saveRole(RoleSaveRequestDto requestDto) {
        log.info("Saving new role {} to the database", requestDto.getName());
        Role savedRole = roleRepository.save(requestDto.toEntity());
        return new RoleResponseDto(savedRole);
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        log.info("Adding role {} to user {}", roleName, email);
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("해당 사원을 찾을 수 없습니다. email=" + email));
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RoleNotFoundException("해당 권한을 찾을 수 없습니다. role_name=" + roleName));
        user.getRoles().add(role);
    }

    @Transactional(readOnly = true)
    @Override
    public UserResponseDto getUserByEmail(String email) {
        log.info("Fetching user {}", email);
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("해당 사원을 찾을 수 없습니다. email=" + email));
        return new UserResponseDto(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserResponseDto> getUsersDESC() {
        log.info("Fetching users");
        return userRepository.findAllDesc().stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }
}
