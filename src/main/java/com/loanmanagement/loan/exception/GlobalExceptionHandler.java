package com.loanmanagement.loan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = LoanCannotAcceptException.class)
    @ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
    public ErrorResponse handleLoanNotAcceptException(LoanCannotAcceptException ex) {
        ErrorResponse err = ErrorResponse.builder(ex, HttpStatus.NOT_ACCEPTABLE, "Loan payment cannot be accepted due to payment date passes the due date").build();
        return err;
    }


}
