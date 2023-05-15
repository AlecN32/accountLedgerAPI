package com.banking.accountledger.Controller;


import com.banking.accountledger.model.Account;
import com.banking.accountledger.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/all")
    public ResponseEntity<List<Account>> getAllAccounts(){
        List<Account> accounts =  accountService.findAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/account-number/{account-number}")
    public ResponseEntity<Account> getAccount(@PathVariable("accountNumber") Long accountNumber){
        Account account =  accountService.findAccountByAccNum(accountNumber);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping("/{accountID}")
    public ResponseEntity<Account> getAccountById(@PathVariable("accountID") Long accountID){
        Account account =  accountService.findAccountByAccountId(accountID);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PostMapping("/create-account")
    public ResponseEntity<Account> createAccount(@RequestBody Account account){
        Account newAccount = accountService.createAccount(account);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }


    @PutMapping("/update-account")
    public ResponseEntity<Account> updateAccount(@RequestBody Account account){
        Account updateAccount = accountService.updateAccount(account);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @DeleteMapping("/delete-account/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable("id") Long id){
        accountService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
