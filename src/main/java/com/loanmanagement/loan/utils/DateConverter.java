package com.loanmanagement.loan.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Component
public class DateConverter {
    public static Boolean isPaymentDateBeforeDueDate(String dueDate, String paymentDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        Date convertedDueDate = formatter.parse(dueDate);
        Date convertedPaymentDate = formatter.parse(paymentDate);
        return convertedPaymentDate.before(convertedDueDate);
    }
}
