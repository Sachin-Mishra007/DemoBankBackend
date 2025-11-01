package com.sachin.demobank.DTO.ResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponseDTO {
    private Long id;
    private String sourceAccount;
    private String destinationAccount;
    private String transactionStatus;
    private String transactionType;
    private LocalDateTime timestamp;
    private BigDecimal amount;
    private String remarks;

}
