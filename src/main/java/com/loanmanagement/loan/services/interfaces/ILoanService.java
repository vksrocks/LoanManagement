package com.loanmanagement.loan.services.interfaces;

import com.loanmanagement.loan.models.LoanEntity;
import com.loanmanagement.loan.requests.LoanRequest;
import com.loanmanagement.loan.responses.GroupByInterestPerDayResponse;
import com.loanmanagement.loan.responses.GroupByLenderIdResponse;
import com.loanmanagement.loan.responses.GroupByPenaltyPerDayResponse;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.ParseException;
import java.util.List;

public interface ILoanService {
    public String createLoanEntry(LoanRequest loanRequest) throws ParseException;

    public Double getAggregationOnInterest();

    List<GroupByLenderIdResponse> findAllLoanDetailsGroupByLenderId();

    List<GroupByInterestPerDayResponse> findAllLoanDetailsGroupByInterest();

    List<GroupByPenaltyPerDayResponse> findAllLoanDetailsGroupByPenalty();

}
