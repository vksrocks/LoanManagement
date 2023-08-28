package com.loanmanagement.loan.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "loan_management")
public class LoanEntity {
    @Id
    @Column(name = "loan ID")
    private String loanId;

    @Column(name = "Customer id")
    private String customerId;

    @Column(name = "Lender Id")
    private String lenderId;

    @Column(name = "Amount")
    private Double amount;

    @Column(name = "Remaining Amount")
    private Double remainingAmount;

    @Column(name = "Payment Date")
    private String paymentDate;

    @Column(name = "Interest Per Day(%)")
    private Double interestPerDay;

    @Column(name = "Due Date")
    private String dueDate;

    @Column(name = "Penalty/Day (%Day)")
    private Double penaltyPerDay;

    @Column(name = "Cancel")
    private Boolean cancel;
}
