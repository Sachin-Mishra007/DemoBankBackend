package com.sachin.demobank.DTO.RequestDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateAccountDTO {
    @NotBlank(message="Account Status Cannot be blank")
    private String status;

}
