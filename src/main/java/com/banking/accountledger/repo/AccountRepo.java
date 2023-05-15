package com.banking.accountledger.repo;

import com.banking.accountledger.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account, Long> {
    Optional<Account> findAccountByAccountNumber(Long accountNumber);

    Optional<Account>  findAccountByAccountID(Long id);
}
