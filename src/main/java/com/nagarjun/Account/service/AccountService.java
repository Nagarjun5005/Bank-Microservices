package com.nagarjun.Account.service;

import com.nagarjun.Account.dto.CustomerDto;

public interface AccountService {

    public void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);
}
