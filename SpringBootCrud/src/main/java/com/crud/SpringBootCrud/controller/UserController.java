package com.crud.SpringBootCrud.controller;

import apiResponse.ApiResponse;
import com.crud.SpringBootCrud.dto.RegistrationDto;
import com.crud.SpringBootCrud.dto.UserDto;
import com.crud.SpringBootCrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value ="/create",method = RequestMethod.POST,  produces = "application/json")
    public ResponseEntity<ApiResponse<UserDto>> create(@RequestBody UserDto userDto) {
        ApiResponse<UserDto> responseDTO = userService.create(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @RequestMapping(value ="/update",method = RequestMethod.PUT,  produces = "application/json" )

    public ResponseEntity<ApiResponse<UserDto>> update(@PathVariable Long id, @RequestBody UserDto userDto) {
        ApiResponse<UserDto> responseDTO = userService.update(id, userDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @RequestMapping(value ="/list",method = RequestMethod.GET,  produces = "application/json")

    public ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers() {
        ApiResponse<List<UserDto>> responseDTO =   userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
    @RequestMapping(value ="/list/{id}",method = RequestMethod.GET,  produces = "application/json" )

    public ResponseEntity<ApiResponse<UserDto>> getUserById(@PathVariable Long id) {
        ApiResponse<UserDto> responseDTO =  userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

@RequestMapping(value ="/delete{id}",method = RequestMethod.DELETE,  produces = "application/json" )

public ResponseEntity<ApiResponse<UserDto>> deleteUserById(@PathVariable Long id) {
        ApiResponse<UserDto> responseDTO = userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }


    @RequestMapping(value ="/login",method = RequestMethod.POST,  produces = "application/json" )

    public ApiResponse<UserDto> loginUser(@RequestParam String email, @RequestParam String password) {
        ApiResponse<UserDto> responseDTO = userService.loginUser(email,password);

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO).getBody();
    }

    @RequestMapping(value ="/register",method = RequestMethod.POST,  produces = "application/json" )
    public ApiResponse<UserDto> registerUser(@RequestBody RegistrationDto registrationDto) {
        ApiResponse<UserDto> responseDTO = userService.registerUser(registrationDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO).getBody();
    }
}









