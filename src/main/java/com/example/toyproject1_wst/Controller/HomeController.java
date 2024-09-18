package com.example.toyproject1_wst.Controller;

import com.example.toyproject1_wst.Model.Account;
import com.example.toyproject1_wst.Service.AccountService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final AccountService accountService;

    @GetMapping("/")
    public String index(HttpSession session, Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username1 = authentication.getName();  // 세션에서 데이터 가져오기 현재 로그인된 사용자의 아이디

        //Spring security는 기본적으로 anonymousUser 를 리턴해주기 때문에 th : if 조건문만으로는 거를 수 없기 때문에
        //anonymousUser 를 리턴하면 모델에 null 값을 넣어서 값을 없게 만들어 준다.
       if (authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getName())) {
            String username = authentication.getName();  // 세션에서 데이터 가져오기 현재 로그인된 사용자의 이름 (username)
            model.addAttribute("sessionName", username);
        } else {
            model.addAttribute("sessionName", null);  // 로그인되지 않은 경우 null로 설정
        }

       log.info("현재 세션" + username1 );
        return "home";
    }

    @GetMapping("/register")
    public String signUpPage(){
        return "signup";
    }

    @PostMapping("/accounts/update/{userCode}")
    public String getData(@PathVariable int userCode, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String getConnectedId = authentication.getName(); // 로그인된 사용자의 ID를 가져옴

        Optional<Account> accountOpt = accountService.findByUserId(getConnectedId);
        Account account = accountOpt.orElseThrow(() -> new RuntimeException("Account not found"));

        // userCode를 덮어쓰기 전에 의도적으로 사용 여부 확인
        int retrievedUserCode = accountService.findUserCodeByUserId(getConnectedId);

        // Model에 데이터 추가
        model.addAttribute("userInfo", account); // 계정 정보
        model.addAttribute("userCode", String.valueOf(retrievedUserCode)); // userCode를 String으로 추가

        // JSON 응답이 아닌 경우 response 맵 제거

        return "modify"; // modify 페이지로 리다이렉트
     }
    }
