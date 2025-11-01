package com.sachin.demobank.Services.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sachin.demobank.DTO.Mappers.UserMapper;
import com.sachin.demobank.DTO.RequestDTO.CreateUserDTO;
import com.sachin.demobank.DTO.RequestDTO.UpdateUserDTO;
import com.sachin.demobank.DTO.ResponseDTO.UserResponseDTO;
import com.sachin.demobank.Entity.User;
import com.sachin.demobank.Repositories.UserRepository;
import com.sachin.demobank.Services.UserService;
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }

    @Override
    public UserResponseDTO register(CreateUserDTO dto) {
        //Check with email if the use Already exists and then add the user
       if(dto==null) throw new IllegalArgumentException("User Details cannot be null");

       if(dto.getEmail()!=null && userRepository.existsByEmail(dto.getEmail()))
       {
        throw new IllegalArgumentException("Email is already in use");
       }

       User user=UserMapper.toEntity(dto);
       User savedUser=userRepository.save(user);
       return UserMapper.toResponseDTO(savedUser);
    }

    @Override
    public UserResponseDTO getProfile(Long userId) {
        if(userId==null)
        {
            throw new IllegalArgumentException("User Id is not valid ");
        }

        User user=userRepository.findById(userId).orElseThrow(
            ()->new IllegalArgumentException("User not found with UserId"+userId)
        );
        return UserMapper.toResponseDTO(user);
    }

    @Override
    public UserResponseDTO updateUser(Long userId, UpdateUserDTO dto) {
        if(userId==null|| dto==null)
        {
            throw new IllegalArgumentException("UserId or Dto cannot be null");
        }

        User user=userRepository.findById(userId).orElseThrow(
            ()->new IllegalArgumentException("User not found with UserId"+userId)
        );

        if(dto.getEmail()!=null)
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

        User updatedUser=userRepository.save(user);
        return UserMapper.toResponseDTO(updatedUser);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        
        List<User> users=userRepository.findAll();
        return users.stream().map(UserMapper::toResponseDTO).toList();
    }

}
