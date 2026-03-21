package com.nagarjun.Account.repository;

import com.nagarjun.Account.entity.Account;
import com.nagarjun.Account.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Account,Long> {
        Optional<Account> findByCustomerId(Long customerId);
}
