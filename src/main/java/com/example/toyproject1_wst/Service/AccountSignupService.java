package com.example.toyproject1_wst.Service;


import com.example.toyproject1_wst.DTO.AccountSignUpRequest;
import com.example.toyproject1_wst.DTO.AccountSignupResponse;
import com.example.toyproject1_wst.ExceptionUtil.EmailDuplicateException;
import com.example.toyproject1_wst.ExceptionUtil.UserIdDuplicateException;
import com.example.toyproject1_wst.Model.Account;
import com.example.toyproject1_wst.Repository.AccountRepository;
import com.example.toyproject1_wst.Util.Password;
import com.example.toyproject1_wst.Util.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AccountSignupService {
    private final AccountRepository accountRepository;

    @Transactional
    public AccountSignupResponse signup(AccountSignUpRequest request) {
        if (accountRepository.existsByUserId(request.getUserId())) {
            throw new UserIdDuplicateException(request.getUserId());
        }

        if (accountRepository.existsByEmail(request.getEmail())) {
            throw new EmailDuplicateException(request.getEmail());
        }

        Account account = Account.builder()
                .email(request.getEmail())
                .userName(request.getUserName())
                .password(new Password(request.getPassword()))
                .role(Role.USER)
                .build();

        accountRepository.save(account);

        return AccountSignupResponse.from(account);
    }
}