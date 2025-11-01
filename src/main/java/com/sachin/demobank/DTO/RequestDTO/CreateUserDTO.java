package com.sachin.demobank.DTO.RequestDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDTO {
    @NotBlank(message = "Full Name cannot be blank")
    private String fullName;
    @Email(message = "Invalid email address")
    private String email;
    @NotBlank(message="Role cannot be blank")
    private String role;
    @Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$", 
             message="Password must be at least 8 characters long and include at least one uppercase letter, one lowercase letter, one digit, and one special character")
    private String password;
    @Pattern(regexp="^[0-9]{10}$", message="Phone number must be of 10 digits")
    private String phone;
}
