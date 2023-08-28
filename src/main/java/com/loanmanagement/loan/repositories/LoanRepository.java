package com.loanmanagement.loan.repositories;

import com.loanmanagement.loan.models.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoanRepository extends JpaRepository<LoanEntity,String> {

    @Query(value = "SELECT SUM(l.amount * l.interestPerDay / 100) FROM LoanEntity l")
   Double findAggregationOnInterest();

//    @Query("SELECT l.lenderId, l.customerId, l.loanId, l.amount, l.remainingAmount, l.paymentDate, l.interestPerDay, l.dueDate, l.penaltyPerDay, l.cancel " +
//            "FROM LoanEntity l " +
//            "GROUP BY l.lenderId")
@Query("SELECT l.lenderId, " +
        "SUM(l.remainingAmount) AS totalRemainingAmount, " +
        "SUM(l.amount * l.interestPerDay / 100) AS totalInterest, " +
        "SUM(l.remainingAmount * l.penaltyPerDay / 100) AS totalPenalty " +
        "FROM LoanEntity l " +
        "GROUP BY l.lenderId")
    List<Object[]> findAllLoanGroupByLenderId();

    @Query("SELECT l.interestPerDay, " +
            "SUM(l.remainingAmount) AS totalRemainingAmount, " +
            "SUM(l.amount * l.interestPerDay / 100) AS totalInterest, " +
            "SUM(l.remainingAmount * l.penaltyPerDay / 100) AS totalPenalty " +
            "FROM LoanEntity l " +
            "GROUP BY l.interestPerDay")
    List<Object[]> findAllLoanGroupByInterest();

    @Query("SELECT l.interestPerDay, " +
            "SUM(l.remainingAmount) AS totalRemainingAmount, " +
            "SUM(l.amount * l.interestPerDay / 100) AS totalInterest, " +
            "SUM(l.remainingAmount * l.penaltyPerDay / 100) AS totalPenalty " +
            "FROM LoanEntity l " +
            "GROUP BY l.interestPerDay")
    List<Object[]> findAllLoanGroupByPenalty();


}
