package com.loanmanagement.loan.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanResponse {
    private String loadId;
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
