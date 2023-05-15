package com.banking.accountledger.service;

import com.banking.accountledger.enums.ETransactionType;
import com.banking.accountledger.exception.NotFoundException;
import com.banking.accountledger.model.Account;
import com.banking.accountledger.model.Transaction;
import com.banking.accountledger.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionRepo transactionRepo;

    @Autowired
    AccountService accountService;

    public Transaction createTransaction(Transaction transaction){

        Account account = accountService.findAccountByAccountId(transaction.getAccountID());
        account.setAccountBalance(account.getAccountBalance() + transaction.getTransactionAmount());
        accountService.updateAccount(account);

        transaction.setTransactionDate(new Date());
        return transactionRepo.save(transaction);
    }

    public List<Transaction> findAllTransactions(){
        return transactionRepo.findAll();
    }

    public List<Transaction> findTransactionsByAccountID(Long accountID){
        return transactionRepo.findTransactionByAccountID(accountID);
    }

    public Transaction updateTransaction(Transaction transaction){
        return transactionRepo.save(transaction);
    }

    public void deleteTransaction(Long transactionId){

        Transaction transaction = findTransactionById(transactionId);

        Account account = accountService.findAccountByAccountId(transaction.getAccountID());
        if(transaction.getTransactionType().equalsIgnoreCase(ETransactionType.CREDIT.getDescription())){
            account.setAccountBalance(account.getAccountBalance() - transaction.getTransactionAmount());
        }
        else{
            account.setAccountBalance(account.getAccountBalance() + transaction.getTransactionAmount());
        }
        accountService.updateAccount(account);

        transactionRepo.deleteById(transactionId);
    }

    public Transaction findTransactionById(Long transactionId) {
       return transactionRepo.findByTransactionID(transactionId).
               orElseThrow(() -> new NotFoundException("transaction number : " + transactionId + " not found"));
    }

    public List<Transaction> findTransactionByType(String tranType) {
        return transactionRepo.findByTransactionType(tranType);
    }

    public List<Transaction> findTransactionsByDateRange(Long accountID, Date fromDate, Date toDate) {
        return transactionRepo.findTransactionsByDateRange(accountID, fromDate, toDate);
    }
}
