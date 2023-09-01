package com.loanmanagement.loan.controllers;

import com.loanmanagement.loan.models.LoanEntity;
import com.loanmanagement.loan.requests.LoanRequest;
import com.loanmanagement.loan.responses.GroupByInterestPerDayResponse;
import com.loanmanagement.loan.responses.GroupByLenderIdResponse;
import com.loanmanagement.loan.responses.GroupByPenaltyPerDayResponse;
import com.loanmanagement.loan.services.interfaces.ILoanService;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value = "/loan")
public class LoanController {
    @Autowired
    private ILoanService loanService;

    @PostMapping
    public ResponseEntity createLoanEntry(@RequestBody @Valid LoanRequest loanRequest) throws ParseException {
        String response = loanService.createLoanEntry(loanRequest);
        return ResponseEntity.accepted().body(loanRequest);
    }

    @GetMapping(value = "/group_by_lender_id")
    public List<GroupByLenderIdResponse> getAllLoanDetailsGroupByLenderId() {
        return loanService.findAllLoanDetailsGroupByLenderId();
    }

    @GetMapping(value = "/group_by_interest")
    public List<GroupByInterestPerDayResponse> getAllLoanDetailsGroupByInterest() {
        return loanService.findAllLoanDetailsGroupByInterest();
    }
    @GetMapping(value = "/group_by_penalty")
    public List<GroupByPenaltyPerDayResponse> getAllLoanDetailsGroupByPenalty() {
        return loanService.findAllLoanDetailsGroupByPenalty();
    }
}
