package com.midas.userservice.web;

import com.midas.userservice.service.UserService;
import com.midas.userservice.web.dto.users.RoleToUserDto;
import com.midas.userservice.web.dto.users.UserResponseDto;
import com.midas.userservice.web.dto.users.UserSaveRequestDto;
import com.midas.userservice.web.dto.users.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class UserApiController {
    private final UserService userService;

    @PostMapping("/v1/users")
    public Long save(@Valid @RequestBody UserSaveRequestDto requestDto) {
        return userService.saveUser(requestDto);
    }

    @PutMapping("/v1/users/{id}")
    public Long update(@PathVariable Long id, @RequestBody UserUpdateRequestDto requestDto) {
        return userService.update(id, requestDto);
    }

    @DeleteMapping("/v1/users/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/v1/users")
    public List<UserResponseDto> findAllDesc() {
        return userService.findAllDesc();
    }

    @GetMapping("/v1/users/{id}")
    public UserResponseDto findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("/role/addtouser")
    public void addRoleToUser(@RequestBody RoleToUserDto requestDto) {
        userService.addRoleToUser(requestDto);
    }
}
