package Controller;

import DTO.AccountSignUpRequest;
import DTO.AccountSignupResponse;
import Service.AccountSignupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AccountSignupController {

    private final AccountSignupService accountSignupService;

    @PostMapping("/User")
    public ResponseEntity<AccountSignupResponse> signup(
            @RequestBody @Valid AccountSignUpRequest request) {

        AccountSignupResponse response = accountSignupService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}


