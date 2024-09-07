package com.example.toyproject1_wst.Service;

import com.example.toyproject1_wst.Model.Account;
import com.example.toyproject1_wst.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account saveAccout(Account account){ // 유저 create
        return accountRepository.save(account);
    }


}
