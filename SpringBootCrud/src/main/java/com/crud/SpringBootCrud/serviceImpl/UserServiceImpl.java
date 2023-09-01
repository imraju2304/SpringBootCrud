package com.crud.SpringBootCrud.serviceImpl;

import apiResponse.ApiResponse;
import com.crud.SpringBootCrud.dto.RegistrationDto;
import com.crud.SpringBootCrud.dto.UserDto;
import com.crud.SpringBootCrud.entities.UserEntity;
import com.crud.SpringBootCrud.repository.UserRepository;
import com.crud.SpringBootCrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ApiResponse<UserDto> create(UserDto userDto) {
        ApiResponse<UserDto> response = new ApiResponse<>();
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDto.getName());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setGender(userDto.getGender());
        userEntity.setActive(userDto.getActive());
        UserEntity createdUserEntity = userRepository.save(userEntity);
        userDto.setId(createdUserEntity.getId());

        response.setStatus(200);
        response.setData(userDto);
        response.setMessage("User created successfully");
        return response;
    }

    @Override
    public ApiResponse<UserDto> update(Long id, UserDto userDto) {
        ApiResponse<UserDto> response = new ApiResponse<>();

        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            UserEntity existingUserEntity = optionalUser.get();
            existingUserEntity.setName(userDto.getName());
            existingUserEntity.setEmail(userDto.getEmail());
            existingUserEntity.setPassword(userDto.getPassword());
            existingUserEntity.setGender(userDto.getGender());
            existingUserEntity.setActive(userDto.getActive());
            UserEntity updatedUserEntity = userRepository.save(existingUserEntity);

            response.setStatus(201);
            response.setData(updatedUserEntity);
            response.setMessage("User updated successfully");
        } else {
            response.setStatus(404);
            response.setMessage("User with ID " + id + " not found");
        }
        return response;
    }

    @Override
    public ApiResponse<List<UserDto>> getAllUsers() {
        ApiResponse<List<UserDto>> response = new ApiResponse<>();
        List<UserEntity> userEntities = userRepository.findAll();

        List<UserDto> userDtos = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            UserDto userDto = new UserDto();
            userDto.setName(userEntity.getName());
            userDto.setEmail(userEntity.getEmail());
            userDto.setPassword(userEntity.getPassword());
            userDto.setGender(userEntity.getGender());
            userDto.setActive(userEntity.getActive());
            userDtos.add(userDto);
        }
        response.setStatus(200);
        response.setData(userDtos);
        response.setMessage("users retrieved successfully");
        return response;
    }

    @Override
    public ApiResponse<UserDto> getUserById(Long id) {
        ApiResponse<UserDto> response = new ApiResponse<>();
        Optional<UserEntity> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            UserEntity existingUserEntity = optionalUser.get();
            UserDto userDto = new UserDto();
            userDto.setName(existingUserEntity.getName());
            userDto.setEmail(existingUserEntity.getEmail());
            userDto.setPassword(existingUserEntity.getPassword());
            userDto.setGender(existingUserEntity.getGender());
            userDto.setActive(existingUserEntity.getActive());

            response.setStatus(200);
            response.setData(userDto);
            response.setMessage("User retrieved successfully");
        } else {
            response.setStatus(404);
            response.setMessage("User not found");
        }

        return response;
    }

    @Override
    public ApiResponse<UserDto> deleteUserById(Long id) {
        ApiResponse<UserDto> response = new ApiResponse<>();
        Optional<UserEntity> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            UserEntity existingUserEntity = optionalUser.get();
            userRepository.delete(existingUserEntity);
            response.setData(existingUserEntity);
            response.setStatus(200);
            response.setMessage("User deleted successfully");
        } else {
            response.setStatus(404);
            response.setMessage("User not found");
        }
        return response;
    }

    @Override
    public ApiResponse<UserDto> loginUser(String email, String password) {
        ApiResponse<UserDto> response = new ApiResponse<>();

        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity != null) {
            if (password.equals(userEntity.getPassword())) {
                UserDto userDto = new UserDto();
                userDto.setEmail(userEntity.getEmail());
                userDto.setPassword(userEntity.getPassword());
                response.setStatus(200);
                response.setData(userDto);
                response.setMessage("Login successful");
            } else {
                response.setStatus(401);
                response.setMessage("Incorrect password");
            }
        } else {
            response.setStatus(404);
            response.setMessage("User not found");
        }
        return response;
    }

    @Override
    public ApiResponse<UserDto> registerUser(RegistrationDto registrationDto) {
        ApiResponse<UserDto> response = new ApiResponse<>();
        if (userRepository.existsByEmail(registrationDto.getEmail())) {
            response.setStatus(400);
            response.setMessage("Email address is already in use");
            return response;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setName(registrationDto.getName());
        userEntity.setEmail(registrationDto.getEmail());
        userEntity.setPassword(registrationDto.getPassword());
        userEntity.setGender(registrationDto.getGender());
        userRepository.save(userEntity);

        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setName(userEntity.getName());
        userDto.setEmail(userEntity.getEmail());
        userDto.setGender(userEntity.getGender());
        response.setStatus(201);
        response.setData(userDto);
        response.setMessage("User registered successfully");
        return response;
    }
}
