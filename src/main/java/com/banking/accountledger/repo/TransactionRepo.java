package com.banking.accountledger.repo;

import com.banking.accountledger.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepo extends JpaRepository <Transaction, Long> {

    List<Transaction> findByTransactionType(String tranType);

    Optional<Transaction> findByTransactionID(Long transactionId);

    @Query("Select c FROM Transaction c WHERE c.accountID = ?1 and c.transactionDate BETWEEN  ?2 AND ?3")
    List<Transaction> findTransactionsByDateRange(Long accountID, Date fromDate, Date toDate);

    List<Transaction> findTransactionByAccountID(Long accountID);
}
