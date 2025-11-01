package com.sachin.demobank.DTO.RequestDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequestDTO {
    @Email(message="Invalid Email")
    @NotBlank(message="Please provide Email")
    private String email;
    @NotBlank(message="Please provide Password")
    private String password;

}
