package com.midas.userservice.web;

import com.midas.userservice.service.UserService;
import com.midas.userservice.web.dto.UserResponseDto;
import com.midas.userservice.web.dto.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class UserApiController {
    private final UserService userService;

    @PostMapping("/v1/users")
    public Long save(@RequestBody UserSaveRequestDto requestDto) {
        return userService.save(requestDto);
    }

    @GetMapping("/v1/users")
    public List<UserResponseDto> findAllDesc() {
        return userService.findAllDesc();
    }
}
