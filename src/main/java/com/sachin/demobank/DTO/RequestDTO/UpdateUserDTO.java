package com.sachin.demobank.DTO.RequestDTO;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserDTO {
    private String fullName;
    @Email
    private String email;
    private String phone;

}
