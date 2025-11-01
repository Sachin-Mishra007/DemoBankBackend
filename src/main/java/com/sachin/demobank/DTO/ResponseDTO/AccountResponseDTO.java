package com.sachin.demobank.DTO.ResponseDTO;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountResponseDTO {

    private Long id;
    private String accountNuber;
    private BigDecimal balance;
    private String accountType;;
    private String accountStatus;
    private Long userId;
}
