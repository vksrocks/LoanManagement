package com.loanmanagement.loan.requests;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanRequest {
    private String loanId;
    private String customerId;
    private String lenderId;
    @Min(value = 0, message = "amount should be greater than zero")
    private Double amount;
    @Min(value = 0, message = "Remaining amount should be greater than zero")
    private Double remainingAmount;
    private String paymentDate;
    @Min(value = 0, message = "Interest rate should be greater than zero")
    private Double interestPerDay;
    private String dueDate;
    @Min(value = 0, message = "Penalty per day should be greater than zero")
    private Double penaltyPerDay;
    private Boolean cancel;
}
