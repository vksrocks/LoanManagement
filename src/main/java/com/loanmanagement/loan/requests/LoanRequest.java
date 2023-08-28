package com.loanmanagement.loan.requests;

import jakarta.persistence.Column;
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
    private Double amount;
    private Double remainingAmount;
    private String paymentDate;
    private Double interestPerDay;
    private String dueDate;
    private Double penaltyPerDay;
    private Boolean cancel;
}
