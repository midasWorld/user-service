package com.midas.userservice.web.users;

import com.midas.userservice.service.UserService;
import com.midas.userservice.web.ApiResponse;
import com.midas.userservice.web.users.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class UserApiController {
    private final UserService userService;

    @GetMapping("/users")
    public ApiResponse<List<UserResponseDto>> findAllDesc() {
        return ApiResponse.OK(userService.findAllDesc());
    }

    @PostMapping("/signup")
    public ApiResponse<TokenResponseDto> signUp(@RequestBody SignUpRequestDto requestDto) {
        return ApiResponse.OK(userService.signUp(requestDto));
    }

    @PostMapping("/signin")
    public ApiResponse<TokenResponseDto> signIn(@RequestBody SignInReqestDto requestDto) {
        return ApiResponse.OK(userService.signIn(requestDto));
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
