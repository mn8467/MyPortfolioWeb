package com.example.toyproject1_wst.Service;

import com.example.toyproject1_wst.Model.Account;
import com.example.toyproject1_wst.Repository.AccountRepository;
import com.example.toyproject1_wst.Util.AccountType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountSignUpService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public Account saveAccount(Account account){ // 회원가입 시 비밀번호 암호화
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setAuthority(AccountType.ROLE_ADMIN);
        return accountRepository.save(account);
    }


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Account account = accountRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        return toUserDetail(account);
    }

    private UserDetails toUserDetail(Account account) {
        return User.builder()
                .username(account.getUserId()) //username이라고 되어있지만 userId 를 의미하는 것
                .password(account.getPassword())
                .authorities(new SimpleGrantedAuthority(account.getAuthority().getValue()))
                .build();
    }


}
