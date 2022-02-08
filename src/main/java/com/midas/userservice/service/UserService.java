package com.midas.userservice.service;

import com.midas.userservice.domain.users.Role;
import com.midas.userservice.domain.users.RoleRepository;
import com.midas.userservice.domain.users.UserEntity;
import com.midas.userservice.domain.users.UserRepository;
import com.midas.userservice.exception.users.RoleNotFoundException;
import com.midas.userservice.exception.users.UserNotFoundException;
import com.midas.userservice.web.dto.users.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Transactional
    public void signUp(SignUpRequestDto requestDto) {
        userRepository.save(requestDto.toEntity(requestDto.getPassword()));
    }

    public UserResponseDto signIn(SignInReqestDto requestDto) {
        UserEntity findUser = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new UserNotFoundException("해당 사원을 찾을 수 없습니다. email=" + requestDto.getEmail()));
        return new UserResponseDto(findUser);
    }

    @Transactional
    public Long saveRole(RoleSaveRequestDto requestDto) {
        return roleRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public void addRoleToUser(RoleToUserDto requestDto) {
        UserEntity user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new UserNotFoundException("해당 사원을 찾을 수 없습니다. email=" + requestDto.getEmail()));
        for (RoleSaveRequestDto role : requestDto.getRoles()) {
            Role findRole = roleRepository.findByName(role.getName())
                    .orElseThrow(() -> new RoleNotFoundException("해당 권한을 찾을 수 없습니다. name=" + role.getName()));
            user.getRoles().add(findRole);
        }
    }
}
