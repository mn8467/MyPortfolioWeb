package com.example.toyproject1_wst.Controller;

import com.example.toyproject1_wst.Model.Account;
import com.example.toyproject1_wst.Service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@RestController
public class SignUpController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/accounts")
    public String SignUpData(@RequestBody Account account,RedirectAttributes redirectAttributes){
     log.info("userId = "+ account.getUserId());
     log.info("userName = "+ account.getUserName());
     log.info("email = "+ account.getEmail());
     accountService.saveAccount(account);
        // 회원가입 완료 후 홈 페이지로 리다이렉트
        redirectAttributes.addFlashAttribute("message", "회원가입이 완료되었습니다.");

        return "redirect:/";
    }

}
