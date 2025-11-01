package com.sachin.demobank.DTO.Mappers;

import com.sachin.demobank.Constants.Role;
import com.sachin.demobank.Constants.UserStatus;
import com.sachin.demobank.DTO.RequestDTO.CreateUserDTO;
import com.sachin.demobank.DTO.RequestDTO.UpdateUserDTO;
import com.sachin.demobank.DTO.ResponseDTO.UserResponseDTO;
import com.sachin.demobank.Entity.User;

public class UserMapper {
    public static User toEntity(CreateUserDTO dto)
    {
        User user=new User();
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(Role.valueOf(dto.getRole()));
        user.setStatus(UserStatus.ACTIVE);
        user.setPhone(dto.getPhone());
        return user;
    }
    public static void updateEntity(User user,UpdateUserDTO dto)
    {
        if(dto.getEmail()!= null)
        {
            user.setEmail(dto.getEmail());
        }
        if(dto.getFullName()!=null)
        {
            user.setFullName(dto.getFullName());
        }
        if(dto.getPhone()!=null)
        {
            user.setPhone(dto.getPhone());
        }
        
    }
    public static UserResponseDTO toResponseDTO(User user)
    {
        return UserResponseDTO.builder().id(user.getId())
                                        .fullName(user.getFullName())
                                        .email(user.getEmail())
                                        .phone(user.getPhone())
                                        .role(user.getRole().toString())
                                        .status(user.getStatus().toString())
                                        .build();
    }

}
