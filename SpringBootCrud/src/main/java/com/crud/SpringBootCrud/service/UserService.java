package com.crud.SpringBootCrud.service;

import apiResponse.ApiResponse;
import com.crud.SpringBootCrud.dto.RegistrationDto;
import com.crud.SpringBootCrud.dto.UserDto;

import java.util.List;

public interface UserService {

    ApiResponse<UserDto> create(UserDto userDto);

    ApiResponse<UserDto> update(Long id, UserDto userDto);

    ApiResponse<List<UserDto>> getAllUsers();

    ApiResponse<UserDto> getUserById(Long id);

    ApiResponse<UserDto> deleteUserById(Long id);

    ApiResponse<UserDto> loginUser(String email, String password);

    ApiResponse<UserDto> registerUser(RegistrationDto registrationDto);
}
