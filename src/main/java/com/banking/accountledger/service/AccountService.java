package com.banking.accountledger.service;

import com.banking.accountledger.exception.NotFoundException;
import com.banking.accountledger.model.Account;
import com.banking.accountledger.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountRepo accountRepo;

    public Account createAccount(Account account){
        account.setDateCreated(new Date());
        return accountRepo.save(account);
    }

    public List<Account> findAllAccounts(){
        return accountRepo.findAll();
    }

    public Account findAccountByAccountId(Long id){
        return accountRepo.findAccountByAccountID(id).
        orElseThrow(() -> new NotFoundException("Account id : " + id + " not found"));
    }

    public Account updateAccount(Account account){
        return accountRepo.save(account);
    }

    public void deleteAccount(Long id){
         accountRepo.deleteById(id);
    }

    public Account findAccountByAccNum(Long accountNum )  {
        return accountRepo.findAccountByAccountNumber(accountNum).
                orElseThrow(() -> new NotFoundException("Account number : " + accountNum + " not found"));
    }

}
