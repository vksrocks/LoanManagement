package com.loanmanagement.loan.services;

import com.loanmanagement.loan.exception.LoanCannotAcceptException;
import com.loanmanagement.loan.models.LoanEntity;
import com.loanmanagement.loan.requests.LoanRequest;
import com.loanmanagement.loan.responses.GroupByInterestPerDayResponse;
import com.loanmanagement.loan.responses.GroupByLenderIdResponse;
import com.loanmanagement.loan.responses.GroupByPenaltyPerDayResponse;
import org.apache.coyote.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoanServiceTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int randomServerPort;

    public static LoanRequest newLoan =new LoanRequest();

    LoanServiceTest() {
    }

    @BeforeEach
    public void setUp()
    {
        newLoan.setAmount(5667d);
        newLoan.setLoanId("L1");
        newLoan.setCancel(true);
        newLoan.setDueDate("21-07-2023");
        newLoan.setCustomerId("C1");
        newLoan.setLenderId("LEN1");
        newLoan.setInterestPerDay(1.0);
        newLoan.setPenaltyPerDay(34.5);
        newLoan.setRemainingAmount(33.55);
        newLoan.setPaymentDate("23-06-2023");
    }

    @Test
    void createLoanEntryWithValidData() {
        ResponseEntity response = restTemplate.postForEntity("http://localhost:" + randomServerPort + "/loan", newLoan, LoanEntity.class);
        assertEquals(202, response.getStatusCodeValue());
        assertEquals(LoanEntity.class, response.getBody().getClass());
    }

    @Test
    void createLoanEntryWithInValidData() {
        ResponseEntity response=null;
        try {
            LoanRequest newLoan = new LoanRequest();
            newLoan.setAmount(5667d);
            newLoan.setLoanId("L1");
            newLoan.setCancel(true);
            newLoan.setDueDate("21-06-2023");
            newLoan.setCustomerId("C1");
            newLoan.setLenderId("LEN1");
            newLoan.setInterestPerDay(-43.7);
            newLoan.setPenaltyPerDay(-34.5);
            newLoan.setRemainingAmount(33.55);
            newLoan.setPaymentDate("23-06-2023");
           response = restTemplate.postForEntity("http://localhost:" + randomServerPort + "/loan", newLoan, LoanEntity.class);
        } catch (LoanCannotAcceptException ex) {
            assertEquals(406, response.getStatusCode());
            assertThat(ex.getMessage(),is("Loan payment cannot be accepted due to payment date passes the due date"));
        }

    }

    @Test
    void findAllLoanDetailsGroupByLenderId() {
        List<GroupByLenderIdResponse> groupByLenderIdResponse = restTemplate.getForObject("http://localhost:" + randomServerPort + "/loan/group_by_lender_id", List.class);
        assertNotNull(groupByLenderIdResponse);
    }

    @Test
    void findAllLoanDetailsGroupByInterest() {
        List<GroupByInterestPerDayResponse> groupByInterestPerDayResponses = restTemplate.getForObject("http://localhost:" + randomServerPort + "/loan/group_by_interest", List.class);
        assertNotNull(groupByInterestPerDayResponses);
    }

    @Test
    void findAllLoanDetailsGroupByPenalty() {
        List<GroupByPenaltyPerDayResponse> groupByPenaltyPerDayResponses = restTemplate.getForObject("http://localhost:" + randomServerPort + "/loan/group_by_penalty", List.class);
        assertNotNull(groupByPenaltyPerDayResponses);
    }
}