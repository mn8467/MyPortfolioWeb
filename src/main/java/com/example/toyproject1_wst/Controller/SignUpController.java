package com.example.toyproject1_wst.Controller;

import com.example.toyproject1_wst.Model.Account;
import com.example.toyproject1_wst.Service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class SignUpController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/accounts")
    public Account SignUpData(@RequestBody Account account){
     log.info("userId = "+ account.getUserId());
     log.info("userName = "+ account.getUserName());
     log.info("email = "+ account.getEmail());
     accountService.saveAccout(account);
        return account;
    }

}
