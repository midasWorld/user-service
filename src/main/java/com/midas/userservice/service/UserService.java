package com.midas.userservice.service;

import com.midas.userservice.domain.users.UserRepository;
import com.midas.userservice.web.dto.UserResponseDto;
import com.midas.userservice.web.dto.UserSaveRequestDto;
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

    public List<UserResponseDto> findAllDesc() {
        return userRepository.findAllDesc().stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }
}
