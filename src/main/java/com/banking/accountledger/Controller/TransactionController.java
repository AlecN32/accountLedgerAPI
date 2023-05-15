package com.banking.accountledger.Controller;

import com.banking.accountledger.model.Transaction;
import com.banking.accountledger.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @GetMapping("/all")
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        List<Transaction> transactions =  transactionService.findAllTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/get-transactions-by-account/{accountID}")
    public ResponseEntity<List<Transaction>> getAllTransactions(@PathVariable("accountID") Long accountID){
        List<Transaction> transactions =  transactionService.findTransactionsByAccountID(accountID);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/find/{transactionId}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable("transactionId") Long transactionId){
        Transaction transaction =  transactionService.findTransactionById(transactionId);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @PostMapping("/create-transaction")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction Transaction){
        Transaction newTransaction = transactionService.createTransaction(Transaction);
        return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
    }

    @PutMapping("/update-transaction")
    public ResponseEntity<Transaction> updateTransaction(@RequestBody Transaction Transaction){
        Transaction updateTransaction = transactionService.updateTransaction(Transaction);
        return new ResponseEntity<>(updateTransaction, HttpStatus.OK);
    }

    @DeleteMapping("/delete-transaction/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable("id") Long transactionId){
        transactionService.deleteTransaction(transactionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/transaction-type/{tranType}")
    public ResponseEntity<List<Transaction>> getAllTransactionByType(@PathVariable("tranType") String tranType){
        List<Transaction> transactions =  transactionService.findTransactionByType(tranType);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/transactionsByDateRange/{accountID}/{fromDate}/{toDate}")
    public ResponseEntity<List<Transaction>> getTransactionsByDateRange(@PathVariable("accountID") Long accountID,
                                                                  @PathVariable("fromDate") @DateTimeFormat(pattern= "yyyy-MM-dd")  Date fromDate,
                                                                  @PathVariable("toDate") @DateTimeFormat(pattern= "yyyy-MM-dd")  Date toDate  ){
        List<Transaction> transactions =  transactionService.findTransactionsByDateRange(accountID, fromDate, toDate);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

}
