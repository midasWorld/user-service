package com.midas.userservice.web;

import com.midas.userservice.service.UserService;
import com.midas.userservice.web.dto.users.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class UserApiController {
    private final UserService userService;

    @GetMapping("/users")
    public List<UserResponseDto> findAllDesc() {
        return userService.findAllDesc();
    }

    @PostMapping("/signup")
    public TokenResponseDto signUp(@RequestBody SignUpRequestDto requestDto) {
        return userService.signUp(requestDto);
    }

    @PostMapping("/signin")
    public TokenResponseDto signIn(@RequestBody SignInReqestDto requestDto) {
        return userService.signIn(requestDto);
    }

    @PostMapping("/roles")
    public void saveRole(@RequestBody RoleSaveRequestDto requestDto) {
        userService.saveRole(requestDto);
    }

    @PostMapping("/roles/addtouser")
    public void addRoleToUser(@RequestBody RoleToUserDto requestDto) {
        userService.addRoleToUser(requestDto);
    }
}
