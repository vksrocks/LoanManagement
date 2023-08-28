package com.loanmanagement.loan.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupByLenderIdResponse {
    private String lenderId;
    private Double totalRemainingAmount;
    private Double totalInterest;
    private Double totalPenalty;

}
