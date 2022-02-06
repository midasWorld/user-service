package com.midas.userservice.service;

import com.midas.userservice.web.dto.auth.RoleResponseDto;
import com.midas.userservice.web.dto.auth.RoleSaveRequestDto;
import com.midas.userservice.web.dto.auth.UserResponseDto;
import com.midas.userservice.web.dto.auth.UserSaveRequestDto;

import java.util.List;

public interface UserService {
    UserResponseDto saveUser(UserSaveRequestDto requestDto);
    RoleResponseDto saveRole(RoleSaveRequestDto requestDto);
    void addRoleToUser(String email, String roleName);
    UserResponseDto getUserByEmail(String email);
    List<UserResponseDto> getUsersDESC();
}
