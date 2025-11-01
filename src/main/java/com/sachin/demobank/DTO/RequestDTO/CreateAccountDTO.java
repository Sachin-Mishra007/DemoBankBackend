package com.sachin.demobank.DTO.RequestDTO;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAccountDTO {

    @NotNull(message="UserId cannot be null")
    private Long userId;
    @NotBlank(message="Account type cannot be blank")
    private String accountType;
    @DecimalMin(value="0.0",message="Initial balance cannot be negative")
    private BigDecimal initalBalance;

}
