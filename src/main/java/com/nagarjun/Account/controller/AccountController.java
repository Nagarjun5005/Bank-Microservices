package com.nagarjun.Account.controller;


import com.nagarjun.Account.constants.AccountsConstants;
import com.nagarjun.Account.dto.CustomerDto;
import com.nagarjun.Account.dto.ResponseDto;
import com.nagarjun.Account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class AccountController {


    private final AccountService accountService;

       //create account
    @PostMapping("/create")
    public ResponseEntity<ResponseDto>createAccount(@RequestBody CustomerDto customerDto){
         accountService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
    }


}
