package com.midas.userservice.service;

import com.midas.userservice.domain.users.*;
import com.midas.userservice.exception.users.RoleNotFoundException;
import com.midas.userservice.exception.users.UserNotFoundException;
import com.midas.userservice.util.JwtUtil;
import com.midas.userservice.web.dto.users.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthRepository authRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public TokenResponseDto signUp(SignUpRequestDto requestDto) {
        // 사원 등록
        UserEntity savedUser = userRepository.save(requestDto.toEntity(passwordEncoder.encode(requestDto.getPassword())));

        // 사원 권한 등록
        for (RoleSaveRequestDto role : requestDto.getRoles()) {
            Role findRole = roleRepository.findByName(role.getName())
                    .orElseThrow(() -> new RoleNotFoundException("해당 권한을 찾을 수 없습니다. name=" + role.getName()));
            savedUser.getRoles().add(findRole);
        }

        // Access 토큰 생성
        String accessToken = jwtUtil.createToken(
                savedUser.getEmail(),
                savedUser.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList())
        );

        // Refresh 토큰 생성
        String refreshToken = jwtUtil.createRefreshToken(savedUser.getEmail());

        // Refresh 토큰 정보 DB에 저장
        authRepository.save(AuthEntity.builder()
                .user(savedUser)
                .refreshToken(refreshToken)
                .build());

        return new TokenResponseDto(savedUser.getEmail(), accessToken, refreshToken);
    }

    @Transactional
    public TokenResponseDto signIn(SignInReqestDto requestDto) {
        UserEntity findUser = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new UserNotFoundException("해당 사원을 찾을 수 없습니다. email=" + requestDto.getEmail()));

        AuthEntity auth = authRepository.findByUserId(findUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 사원의 토큰 정보를 찾을 수 없습니다. id=" + findUser.getId()));

        if (!passwordEncoder.matches(requestDto.getPassword(), findUser.getEncryptedPwd())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String accessToken = jwtUtil.createToken(
            findUser.getEmail(),
            findUser.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList())
        );

        String refreshToken = auth.getRefreshToken();

        if (jwtUtil.isValidateRefreshToken(refreshToken)) {
            refreshToken = jwtUtil.createRefreshToken(findUser.getEmail());
            auth.updateRefreshToken(refreshToken);
        }

        return new TokenResponseDto(findUser.getEmail(), accessToken, refreshToken);
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

    public List<UserResponseDto> findAllDesc() {
        return userRepository.findAllDesc().stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }
}
