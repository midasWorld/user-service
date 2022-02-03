package com.midas.userservice.service;

import com.midas.userservice.domain.users.UserEntity;
import com.midas.userservice.domain.users.UserRepository;
import com.midas.userservice.web.dto.UserResponseDto;
import com.midas.userservice.web.dto.UserSaveRequestDto;
import com.midas.userservice.web.dto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Long save(UserSaveRequestDto requestDto) {
        return userRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, UserUpdateRequestDto requestDto) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사원은 존재하지 않습니다. id=" + id));
        user.update(requestDto.getName(), requestDto.getPassword());
        return user.getId();
    }

    public List<UserResponseDto> findAllDesc() {
        return userRepository.findAllDesc().stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }
}
