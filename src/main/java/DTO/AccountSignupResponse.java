package DTO;

import Model.Account;
import Util.Password;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AccountSignupResponse {

    private String userId;
    private String userName;
    private String email;
    private Password password;

    public static AccountSignupResponse from(Account accounts) {
        return AccountSignupResponse.builder()
                .userId(accounts.getUserId())
                .userName(accounts.getUserName())
                .password(accounts.getPassword())
                .build();
    }
}