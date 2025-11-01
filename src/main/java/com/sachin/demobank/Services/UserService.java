package com.sachin.demobank.Services;

import java.util.List;

import com.sachin.demobank.DTO.RequestDTO.CreateUserDTO;
import com.sachin.demobank.DTO.RequestDTO.UpdateUserDTO;
import com.sachin.demobank.DTO.ResponseDTO.UserResponseDTO;

public interface UserService {
    UserResponseDTO register(CreateUserDTO dto);
    UserResponseDTO getProfile (Long userId);
    UserResponseDTO updateUser(Long userId, UpdateUserDTO dto);
    List<UserResponseDTO> getAllUsers();

}
