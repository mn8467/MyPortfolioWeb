package com.example.toyproject1_wst.Controller;

import com.example.toyproject1_wst.Model.Account;
import com.example.toyproject1_wst.Service.AccountService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AccountController {


    public final AccountService accountService;

    @PostMapping("/accounts")
    public ResponseEntity<Map<String, String>> SignUpData(@RequestBody Account account) {
        log.info("userId = "+ account.getUserId());
        log.info("userName = "+ account.getUserName());
        log.info("email = "+ account.getEmail());
        accountService.saveAccount(account);

        // 리다이렉트 URL을 JSON으로 응답
        Map<String, String> response = new HashMap<>();
        response.put("message", "회원가입이 완료되었습니다.");
        response.put("redirectUrl", "/");

        return ResponseEntity.ok(response);
    }


}
