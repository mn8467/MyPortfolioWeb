package com.example.toyproject1_wst.Controller;

import com.example.toyproject1_wst.Model.Account;
import com.example.toyproject1_wst.Service.AccountService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class SignUpController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/accounts")
    public  ResponseEntity<Map<String, String>> SignUpData(@RequestBody Account account) {
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
