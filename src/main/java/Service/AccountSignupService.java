package Service;


import DTO.AccountSignUpRequest;
import DTO.AccountSignupResponse;
import ExceptionUtil.EmailDuplicateException;
import ExceptionUtil.UserIdDuplicateException;
import Model.Account;
import Repository.AccountRepository;
import Util.Password;
import Util.Role;
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