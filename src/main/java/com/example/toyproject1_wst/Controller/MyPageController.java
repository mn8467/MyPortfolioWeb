package com.example.toyproject1_wst.Controller;

import com.example.toyproject1_wst.Model.Account;
import com.example.toyproject1_wst.Service.AccountService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MyPageController {  //회원만?

    private final AccountService accountService;


    @GetMapping("/myPage/info")
    public String GetInfoData(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String getConnectedId = authentication.getName(); // 로그인된 사용자의 ID를 가져옴

        Optional<Account> accountOpt = accountService.findByUserId(getConnectedId);
        Account account = accountOpt.orElseThrow(() -> new RuntimeException("Account not found"));

        // userCode를 검증하여 맞는 사용자 정보만 제공하도록 변경
        int retrievedUserCode = accountService.findUserCodeByUserId(getConnectedId);


        // Model에 데이터 추가
        model.addAttribute("userInfo", account); // 계정 정보
        model.addAttribute("userCode", retrievedUserCode); // userCode를 String으로 추가


        // JSON 응답이 아닌 경우 response 맵 제거

        return "mypage"; // modify 페이지로 리다이렉트
    }


    //데이터 받는 곳
    @GetMapping("/myPage/info/edit/{userCode}")
    public String editAccount (@PathVariable int userCode, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String getConnectedId = authentication.getName(); // 로그인된 사용자의 ID를 가져옴

        Optional<Account> accountOpt = accountService.findByUserId(getConnectedId);
        Account account = accountOpt.orElseThrow(() -> new RuntimeException("Account not found"));

        // userCode를 검증하여 맞는 사용자 정보만 제공하도록 변경
        int retrievedUserCode = accountService.findUserCodeByUserId(getConnectedId);

        // Model에 데이터 추가
        model.addAttribute("userInfo", account); // 계정 정보

        model.addAttribute("userCode", retrievedUserCode); // userCode 추가

        // edit.html 뷰를 반환
        return "edit"; // Thymeleaf 템플릿 이름
    }

    @RequestMapping(value = "/myPage/edit/data",method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, String> getUpdateData(@RequestBody Account account) {

        accountService.updateAccount(account); // 비밀번호 엔코드 후 저장
        log.info("userCode = " + account.getUserCode());
        log.info("userId = " + account.getUserId());
        log.info("userName = " + account.getUserName());
        log.info("email = " + account.getEmail());

        // 응답에 리다이렉트 URL을 포함
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("result", "ok");
        resultMap.put("redirectUrl", "/"); // 리다이렉트할 URL

        return resultMap;
    }



}
