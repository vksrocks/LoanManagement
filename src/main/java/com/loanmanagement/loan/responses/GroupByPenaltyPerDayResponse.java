package com.loanmanagement.loan.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupByPenaltyPerDayResponse {
    private Double penaltyPerDay;
    private Double totalRemainingAmount;
    private Double totalInterest;
    private Double totalPenalty;
}
