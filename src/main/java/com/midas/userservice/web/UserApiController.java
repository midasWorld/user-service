package com.midas.userservice.web;

import com.midas.userservice.service.UserService;
import com.midas.userservice.web.dto.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class UserApiController {
    private final UserService userService;

    @PostMapping("/v1/users")
    public Long save(@RequestBody UserSaveRequestDto requestDto) {
        return userService.save(requestDto);
    }
}
