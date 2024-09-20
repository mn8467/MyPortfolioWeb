package com.example.toyproject1_wst.Controller;

import com.example.toyproject1_wst.Model.Account;
import com.example.toyproject1_wst.Service.AccountService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.h2.engine.Mode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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



    //데이터 받는 곳
    @GetMapping("/myPage/info/modify/{userCode}")
    public ResponseEntity<Map<String, String>> editAccount (@PathVariable int userCode, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String getConnectedId = authentication.getName(); // 로그인된 사용자의 ID를 가져옴

        Optional<Account> accountOpt = accountService.findByUserId(getConnectedId);
        Account account = accountOpt.orElseThrow(() -> new RuntimeException("Account not found"));

        // userCode를 검증하여 맞는 사용자 정보만 제공하도록 변경
        int retrievedUserCode = accountService.findUserCodeByUserId(getConnectedId);

        // Model에 데이터 추가
        model.addAttribute("userInfo", account); // 계정 정보

        // JSON 응답이 아닌 경우 response 맵 제거
        log.info("userId = "+ account.getUserId());
        log.info("userName = "+ account.getUserName());


        // 리다이렉트 URL을 JSON으로 응답
        Map<String, String> response = new HashMap<>();
        response.put("message", "수정이 완료되었습니다.");
        response.put("redirectUrl", "/");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/myPage/edit/data") // 여기 url로 전송
    public Map<String, String> getUpdateData (@RequestBody Account account){

        // 새로 받은 비밀번호 변경
        String getUpdatePwd = account.getPassword();
        account.setPassword(getUpdatePwd); // 새로운 비밀번호 가져오기
        accountService.updateAccount(account); // 비밀번호 엔코드 후 저장

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("result", "ok");

        return resultMap;

    }


}
