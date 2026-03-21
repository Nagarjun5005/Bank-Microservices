package com.nagarjun.Account.service.impl;

import com.nagarjun.Account.constants.AccountsConstants;
import com.nagarjun.Account.dto.CustomerDto;
import com.nagarjun.Account.entity.Account;
import com.nagarjun.Account.entity.Customer;
import com.nagarjun.Account.exception.CustomerAlreadyExistsException;
import com.nagarjun.Account.mapper.AccountMapper;
import com.nagarjun.Account.mapper.CustomerMapper;
import com.nagarjun.Account.repository.AccountsRepository;
import com.nagarjun.Account.repository.CustomerRepository;
import com.nagarjun.Account.service.AccountService;
import com.nagarjun.Account.service.CustomerService;
import lombok.AllArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;


@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {


    private AccountsRepository accountsRepository;

    private CustomerRepository customerRepository;







    @Override
    public void createAccount(CustomerDto customerDto) {


        //when u create a account u need to create customer and account
        //in repo u have customer
        Customer customer= CustomerMapper.mapToCustomer(customerDto);

        //check if this customer already exists with the same mobile number
       Optional<Customer> mobileNumber = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(mobileNumber.isPresent()){
            throw new CustomerAlreadyExistsException("customer already exists with the registered "+customer.getMobileNumber()+" mobile number");
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));


    }

    private Account createNewAccount(Customer customer) {
        Account account = new Account();
        account.setCustomerId(customer.getCustomerId());
        account.setAccountNumber(generateAccountNumber());
        account.setAccountType(AccountsConstants.SAVINGS);
        account.setBranchAddress(AccountsConstants.ADDRESS);
        account.setCreatedBy("Anonymous");
        account.setCreatedAt(LocalDateTime.now());
        return account;
    }

    private long generateAccountNumber() {
        int year = LocalDateTime.now().getYear(); // 2026
        long sequence = accountsRepository.count() + 1; // 1, 2, 3...
        return Long.parseLong(year + String.format("%08d", sequence));
        // → 202600000001, 202600000002 ...
    }
}
