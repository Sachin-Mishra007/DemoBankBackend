package com.sachin.demobank.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sachin.demobank.DTO.RequestDTO.CreateUserDTO;
import com.sachin.demobank.DTO.RequestDTO.UpdateUserDTO;
import com.sachin.demobank.DTO.ResponseDTO.UserResponseDTO;
import com.sachin.demobank.Services.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userservice;

    public UserController(UserService userservice) {
        this.userservice = userservice;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody CreateUserDTO dto)
    {
        UserResponseDTO response=userservice.register(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long userId)
    {
        UserResponseDTO response=userservice.getProfile(userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUser()
    {
        List<UserResponseDTO> users=userservice.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long userId,@Valid @RequestBody UpdateUserDTO dto)
    {
        UserResponseDTO user=userservice.updateUser(userId, dto);
        return ResponseEntity.ok(user);
    }
    

}
