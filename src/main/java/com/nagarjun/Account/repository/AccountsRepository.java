package com.nagarjun.Account.repository;

import com.nagarjun.Account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsRepository extends JpaRepository<Account,Long> {
}
