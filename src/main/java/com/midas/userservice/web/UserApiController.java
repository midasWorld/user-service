package com.midas.userservice.web;

import com.midas.userservice.service.UserService;
import com.midas.userservice.web.dto.users.*;
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

    @PostMapping("/signup")
    public void signUp(@RequestBody SignUpRequestDto requestDto) {
        userService.signUp(requestDto);
    }

    @PostMapping("/signin")
    public UserResponseDto signIn(@RequestBody SignInReqestDto requestDto) {
        return userService.signIn(requestDto);
    }

    @PostMapping("/v1/roles")
    public void saveRole(@RequestBody RoleSaveRequestDto requestDto) {
        userService.saveRole(requestDto);
    }

    @PostMapping("/v1/roles/addtouser")
    public void addRoleToUser(@RequestBody RoleToUserDto requestDto) {
        userService.addRoleToUser(requestDto);
    }
}
