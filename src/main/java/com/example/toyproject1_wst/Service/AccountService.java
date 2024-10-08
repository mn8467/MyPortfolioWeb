package com.example.toyproject1_wst.Service;

import com.example.toyproject1_wst.Model.Account;
import com.example.toyproject1_wst.Repository.AccountRepository;
import com.example.toyproject1_wst.Util.AccountType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountService implements UserDetailsService {

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

    public Optional<Account> findByUserId (String userId){
        return accountRepository.findByUserId(userId);
    }

    public int findUserCodeByUserId(String userId) {
        // 리포지토리 메서드를 호출하여 userCode를 조회합니다.
        return accountRepository.findUserCodeByUserId(userId);

    }
    public int updateAccount(Account account) {
        // 새로 받은 비밀번호 가져오기
        String getUpdatePwd = account.getPassword();

        // 비밀번호 인코딩
        account.setPassword(passwordEncoder.encode(getUpdatePwd));

       return accountRepository.updateAccount(account.getUserCode(), account.getUserName(), account.getPassword());

    }
}
