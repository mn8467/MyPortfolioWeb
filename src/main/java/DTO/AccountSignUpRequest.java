package DTO;

import Util.Password;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class AccountSignUpRequest {


    @NotBlank
    private String userId;
    @NotBlank
    private String userName;
    @NotBlank
    private String email;
    @NotBlank
    private String password;



}

