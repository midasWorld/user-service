package com.midas.userservice.web;

import com.midas.userservice.service.UserService;
import com.midas.userservice.web.dto.auth.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class UserApiController {
    private final UserService userService;

    @GetMapping("/users")
    public List<UserResponseDto> getUsers() {
        return userService.getUsersDESC();
    }

    @PostMapping("/users")
    public UserResponseDto saveUser(@RequestBody UserSaveRequestDto requestDto) {
        return userService.saveUser(requestDto);
    }

    @PostMapping("/role")
    public RoleResponseDto saveRole(@RequestBody RoleSaveRequestDto requestDto) {
        return userService.saveRole(requestDto);
    }

    @PostMapping("/role/addtouser")
    public void addRoletoUser(@RequestBody RoleToUserDto requestDto) {
        userService.addRoleToUser(requestDto.getEmail(), requestDto.getRoleName());
    }

//    @GetMapping("/role/addtouser")
//    public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
//
//    }
}
