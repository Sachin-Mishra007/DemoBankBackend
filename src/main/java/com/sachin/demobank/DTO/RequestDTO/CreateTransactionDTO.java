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
public class CreateTransactionDTO {
    @NotBlank(message="Source Account cannot be blank")
    private String sourceAccount;
    private String destinationAccount;
    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value="0.1",message="Amount must be greater than zero")
    private BigDecimal amount;
    //We will directly set Transaction Type from service layer
    //@NotBlank(message="Transaction Type cannot be blank")
    //private String transactionType;
    private String remarks;

}
