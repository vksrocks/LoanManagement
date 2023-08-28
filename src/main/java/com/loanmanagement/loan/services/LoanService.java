package com.loanmanagement.loan.services;

import com.loanmanagement.loan.exception.LoanCannotAcceptException;
import com.loanmanagement.loan.models.LoanEntity;
import com.loanmanagement.loan.repositories.LoanRepository;
import com.loanmanagement.loan.requests.LoanRequest;
import com.loanmanagement.loan.responses.GroupByInterestPerDayResponse;
import com.loanmanagement.loan.responses.GroupByLenderIdResponse;
import com.loanmanagement.loan.responses.GroupByPenaltyPerDayResponse;
import com.loanmanagement.loan.services.interfaces.ILoanService;
import com.loanmanagement.loan.utils.DateConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class LoanService implements ILoanService {
    @Autowired
    private LoanRepository loanRepository;
    private static final Logger logger = LoggerFactory.getLogger(LoanService.class);

    @Override
    public String createLoanEntry(LoanRequest loanRequest) throws ParseException {
        if (DateConverter.isPaymentDateBeforeDueDate(loanRequest.getDueDate(), loanRequest.getPaymentDate())) {
            LoanEntity loanEntity = new LoanEntity(loanRequest.getLoanId(), loanRequest.getCustomerId(), loanRequest.getLenderId(), loanRequest.getAmount(), loanRequest.getRemainingAmount(), loanRequest.getPaymentDate(), loanRequest.getInterestPerDay(), loanRequest.getDueDate(), loanRequest.getPenaltyPerDay(), loanRequest.getCancel());
            loanRepository.save(loanEntity);
            return "Loan Entry Successfully created";
        } else {
            logger.atLevel(Level.WARN).log("Payment date passed due date");
            throw new LoanCannotAcceptException();
        }
    }

    @Override
    public Double getAggregationOnInterest() {
        return loanRepository.findAggregationOnInterest();
    }

    @Override
    public List<GroupByLenderIdResponse> findAllLoanDetailsGroupByLenderId() {
        List<Object[]> loanResponse = loanRepository.findAllLoanGroupByLenderId();
        List<GroupByLenderIdResponse> finalResponse = new ArrayList<>();
        for (Object[] result : loanResponse) {
            String lenderId = (String) result[0];
            Double totalRemainingAmount = (Double) result[1];
            Double totalInterest = (Double) result[2];
            Double totalPenalty = (Double) result[3];
            GroupByLenderIdResponse groupByLenderIdResponse = new GroupByLenderIdResponse(lenderId, totalRemainingAmount, totalInterest, totalPenalty);
            finalResponse.add(groupByLenderIdResponse);
        }

        return finalResponse;
    }

    @Override
    public List<GroupByInterestPerDayResponse> findAllLoanDetailsGroupByInterest() {
        List<Object[]> loanResponse = loanRepository.findAllLoanGroupByInterest();
        List<GroupByInterestPerDayResponse> finalResponse = new ArrayList<>();
        for (Object[] result : loanResponse) {
            Double interestPerDay = (Double) result[0];
            Double totalRemainingAmount = (Double) result[1];
            Double totalInterest = (Double) result[2];
            Double totalPenalty = (Double) result[3];
            GroupByInterestPerDayResponse groupByInterestPerDayResponse = new GroupByInterestPerDayResponse(interestPerDay, totalRemainingAmount, totalInterest, totalPenalty);
            finalResponse.add(groupByInterestPerDayResponse);
        }
        return finalResponse;
    }

    @Override
    public List<GroupByPenaltyPerDayResponse> findAllLoanDetailsGroupByPenalty() {
        List<Object[]> loanResponse = loanRepository.findAllLoanGroupByPenalty();
        List<GroupByPenaltyPerDayResponse> finalResponse = new ArrayList<>();
        for (Object[] result : loanResponse) {
            Double penaltyPerDay = (Double) result[0];
            Double totalRemainingAmount = (Double) result[1];
            Double totalInterest = (Double) result[2];
            Double totalPenalty = (Double) result[3];
            GroupByPenaltyPerDayResponse groupByPenaltyPerDayResponse = new GroupByPenaltyPerDayResponse(penaltyPerDay, totalRemainingAmount, totalInterest, totalPenalty);
            finalResponse.add(groupByPenaltyPerDayResponse);
        }
        return finalResponse;
    }


}
